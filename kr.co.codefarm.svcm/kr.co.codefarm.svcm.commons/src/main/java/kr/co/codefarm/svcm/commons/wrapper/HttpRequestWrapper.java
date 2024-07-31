/**
 * 
 */
package kr.co.codefarm.svcm.commons.wrapper;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Dki_s
 *
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper {
	
	private boolean parametersParsed = false;
	 
    private final Charset encoding;
    private final byte[] rawData;
    private final Map<String, ArrayList<String>> parameters = new LinkedHashMap<String, ArrayList<String>>();
    ByteChunk tmpName                                       = new ByteChunk();
    ByteChunk tmpValue                                      = new ByteChunk();
     
    private class ByteChunk {
        private byte[] buff;
        private int start = 0;
        private int end;
 
        private void setByteChunk(byte[] b, int off, int len) {
            this.buff  = b;
            this.start = off;
            this.end   = this.start + len;
        }
 
        private byte[] getBytes() {
            return this.buff;
        }
 
        private int getStart() {
            return this.start;
        }
 
        private int getEnd() {
            return this.end;
        }
 
        private void recycle() {
        	this.buff  = null;
            this.start = 0;
            this.end   = 0;
        }
    }
     
    public HttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        String characterEncoding = request.getCharacterEncoding();
        
        if (StringUtils.isEmpty(characterEncoding)) {
            characterEncoding = StandardCharsets.UTF_8.name();
        }
        
        this.encoding = Charset.forName(characterEncoding);
 
        try {
            InputStream inputStream = request.getInputStream();
            this.rawData            = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw e;
        }
    }
 
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);
        
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
             
            @Override
            public void setReadListener(ReadListener listener) {//
                //
            }
             
            @Override
            public boolean isReady() {
                return false;
            }
             
            @Override
            public boolean isFinished() {
                return false;
            }
        };
         
        return servletInputStream;
    }
 
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
    }
 
    @Override
    public ServletRequest getRequest() {
        return super.getRequest();
    }
     
    @Override
    public String getParameter(String name) {
        if (!parametersParsed) {
            parseParameters();
        }
        
        ArrayList<String> values = this.parameters.get(name);
        
        if (values == null || values.size() == 0) {
            return null;
        }
        
        return values.get(0);
    }
 
    public HashMap<String, String[]> getParameters() {
        if (!parametersParsed) {
            parseParameters();
        }
        
        HashMap<String, String[]> map = new HashMap<String, String[]>(this.parameters.size() * 2);
        
        for (String name : this.parameters.keySet()) {
            ArrayList<String> values = this.parameters.get(name);
            map.put(name, values.toArray(new String[values.size()]));
        }
        
        return map;
    }
 
    @Override
    public Map<String, String[]> getParameterMap() {
        return getParameters();
    }
 
    @Override
    public Enumeration<String> getParameterNames() {
        return new Enumeration<String>() {
            private String[] arr = (String[])(getParameterMap().keySet().toArray(new String[0]));
            private int index    = 0;
 
            @Override
            public boolean hasMoreElements() {
                return index < arr.length;
            }
 
            @Override
            public String nextElement() {
                return arr[index++];
            }
        };
    }
 
    @Override
    public String[] getParameterValues(String name) {
        if (!parametersParsed) {
            parseParameters();
        }
        
        String[] arr = null;
         
        if (this.parameters.get(name) != null) {
            ArrayList<String> values = this.parameters.get(name);
            arr                      = values.toArray(new String[values.size()]);
        }
        
        return arr;
    }
 
    private void parseParameters() {
        parametersParsed = true;
 
        int pos = 0;
        int end = this.rawData.length;
         
        if ((!(super.getContentType().toLowerCase().startsWith("multipart/form-data")) && !(super.getContentType().toLowerCase().startsWith("application/x-www-form-urlencoded"))) || end == 0) {
            Enumeration<String> parameterNames = super.getRequest().getParameterNames();
            
            if (parameterNames.hasMoreElements()) {
                parametersParsed = true;
                
                while (parameterNames.hasMoreElements()) {
                    String parameterName     = parameterNames.nextElement();
                    String[] parameterValues = super.getRequest().getParameterValues(parameterName);
                    
                    this.parameters.put(parameterName, new ArrayList<String>(Arrays.asList(parameterValues)));
                }
            }
            
            return;
        }

        while (pos < end) {
            int nameStart  = pos;
            int nameEnd    = -1;
            int valueStart = -1;
            int valueEnd   = -1;
 
            boolean parsingName       = true;
            boolean decodeName        = false;
            boolean decodeValue       = false;
            boolean parameterComplete = false;
 
            do {
                switch (this.rawData[pos]) {
                    case '=':
                        if (parsingName) {
                            nameEnd     = pos;
                            parsingName = false;
                            valueStart  = ++pos;
                        } else {
                            pos++;
                        }
                        
                        break;
                    case '&':
                        if (parsingName) {
                            nameEnd = pos;
                        } else {
                            valueEnd = pos;
                        }
                        parameterComplete = true;
                        pos++;
                        
                        break;
                    case '%':
                    case '+':
                        if (parsingName) {
                            decodeName = true;
                        } else {
                            decodeValue = true;
                        }
                        pos++;
                        
                        break;
                    default:
                        pos++;
                        
                        break;
                }
            } while (!parameterComplete && pos < end);
 
            if (pos == end) {
                if (nameEnd == -1) {
                    nameEnd = pos;
                } else if (valueStart > -1 && valueEnd == -1) {
                    valueEnd = pos;
                }
            }
 
            if (nameEnd <= nameStart) {
                continue;
            }
 
            tmpName.setByteChunk(this.rawData, nameStart, nameEnd - nameStart);
            
            if (valueStart >= 0) {
                tmpValue.setByteChunk(this.rawData, valueStart, valueEnd - valueStart);
            } else {
                tmpValue.setByteChunk(this.rawData, 0, 0);
            }
 
            try {
                String name;
                String value;
 
                if (decodeName) {
                    name = new String(URLCodec.decodeUrl(Arrays.copyOfRange(tmpName.getBytes(), tmpName.getStart(), tmpName.getEnd())), this.encoding);
                } else {
                    name = new String(tmpName.getBytes(), tmpName.getStart(), tmpName.getEnd() - tmpName.getStart(), this.encoding);
                }
 
                if (valueStart >= 0) {
                    if (decodeValue) {
                        value = new String(URLCodec.decodeUrl(Arrays.copyOfRange(tmpValue.getBytes(), tmpValue.getStart(), tmpValue.getEnd())), this.encoding);
                    } else {
                        value = new String(tmpValue.getBytes(), tmpValue.getStart(), tmpValue.getEnd() - tmpValue.getStart(), this.encoding);
                    }
                } else {
                    value = "";
                }
 
                if (!StringUtils.isEmpty(name)) {
                    ArrayList<String> values = this.parameters.get(name);
                    
                    if (values == null) {
                        values = new ArrayList<String>(1);
                        this.parameters.put(name, values);
                    }
                    if (!StringUtils.isEmpty(value)) {
                        values.add(value);
                    }
                }
            } catch (DecoderException e) {
            	e.printStackTrace();
            }
 
            tmpName.recycle();
            tmpValue.recycle();
        }
    }

}
