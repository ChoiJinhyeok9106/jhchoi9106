!function(t,e){"object"==typeof exports&&"object"==typeof module?module.exports=e():"function"==typeof define&&define.amd?define("vueToast",[],e):"object"==typeof exports?exports.VueToast=e():t.VueToast=e()}("undefined"!=typeof self?self:this,function(){return function(t){var e={};function n(o){if(e[o])return e[o].exports;var i=e[o]={i:o,l:!1,exports:{}};return t[o].call(i.exports,i,i.exports,n),i.l=!0,i.exports}return n.m=t,n.c=e,n.d=function(t,e,o){n.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:o})},n.r=function(t){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},n.t=function(t,e){if(1&e&&(t=n(t)),8&e)return t;if(4&e&&"object"==typeof t&&t&&t.__esModule)return t;var o=Object.create(null);if(n.r(o),Object.defineProperty(o,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var i in t)n.d(o,i,function(e){return t[e]}.bind(null,i));return o},n.n=function(t){var e=t&&t.__esModule?function(){return t.default}:function(){return t};return n.d(e,"a",e),e},n.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},n.p="",n(n.s=1)}([function(t,e,n){},function(t,e,n){"use strict";n.r(e);var o="undefined"!=typeof window?window.HTMLElement:Object;var i=function(t,e,n,o,i,s,r,a){var u,c="function"==typeof t?t.options:t;if(e&&(c.render=e,c.staticRenderFns=n,c._compiled=!0),o&&(c.functional=!0),s&&(c._scopeId="data-v-"+s),r?(u=function(t){(t=t||this.$vnode&&this.$vnode.ssrContext||this.parent&&this.parent.$vnode&&this.parent.$vnode.ssrContext)||"undefined"==typeof __VUE_SSR_CONTEXT__||(t=__VUE_SSR_CONTEXT__),i&&i.call(this,t),t&&t._registeredComponents&&t._registeredComponents.add(r)},c._ssrRegister=u):i&&(u=a?function(){i.call(this,this.$root.$options.shadowRoot)}:i),u)if(c.functional){c._injectStyles=u;var l=c.render;c.render=function(t,e){return u.call(e),l(t,e)}}else{var p=c.beforeCreate;c.beforeCreate=p?[].concat(p,u):[u]}return{exports:t,options:c}}({name:"toast",props:{message:{type:String,required:!0},type:{type:String,default:"success"},position:{type:String,default:"bottom-right"},duration:{type:Number,default:3e3},dismissible:{type:Boolean,default:!0},onClose:{type:Function,default:function(){}},queue:Boolean,container:{type:[Object,Function,o],default:null}},data:function(){return{isActive:!1,parentTop:null,parentBottom:null}},beforeMount:function(){this.setupContainer()},mounted:function(){this.showNotice()},methods:{setupContainer:function(){if(this.parentTop=document.querySelector(".notices.is-top"),this.parentBottom=document.querySelector(".notices.is-bottom"),!this.parentTop||!this.parentBottom){this.parentTop||(this.parentTop=document.createElement("div"),this.parentTop.className="notices is-top"),this.parentBottom||(this.parentBottom=document.createElement("div"),this.parentBottom.className="notices is-bottom");var t=this.container||document.body;t.appendChild(this.parentTop),t.appendChild(this.parentBottom);this.container&&(this.parentTop.classList.add("is-custom-parent"),this.parentBottom.classList.add("is-custom-parent"))}},shouldQueue:function(){return!!this.queue&&(this.parentTop.childElementCount>0||this.parentBottom.childElementCount>0)},close:function(){var t=this;clearTimeout(this.timer),this.isActive=!1,setTimeout(function(){var e;t.$destroy(),void 0!==(e=t.$el).remove?e.remove():e.parentNode.removeChild(e)},150)},showNotice:function(){var t=this;this.shouldQueue()?setTimeout(function(){return t.showNotice()},250):(this.correctParent.insertAdjacentElement("afterbegin",this.$el),this.isActive=!0,this.timer=setTimeout(function(){return t.close()},this.duration))},onClick:function(){this.dismissible&&(this.onClose.apply(null,arguments),this.close())}},computed:{correctParent:function(){switch(this.position){case"top-right":case"top":case"top-left":return this.parentTop;case"bottom-right":case"bottom":case"bottom-left":return this.parentBottom}},transition:function(){switch(this.position){case"top-right":case"top":case"top-left":return{enter:"fadeInDown",leave:"fadeOut"};case"bottom-right":case"bottom":case"bottom-left":return{enter:"fadeInUp",leave:"fadeOut"}}}}},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("transition",{attrs:{"enter-active-class":t.transition.enter,"leave-active-class":t.transition.leave}},[n("div",{directives:[{name:"show",rawName:"v-show",value:t.isActive,expression:"isActive"}],staticClass:"toast",class:["toast-"+t.type,"is-"+t.position],attrs:{role:"alert"},on:{click:t.onClick}},[n("p",{staticClass:"toast-text"},[t._v(t._s(t.message))])])])},[],!1,null,null,null);i.options.__file="Component.vue";var s=i.exports,r=function(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return{open:function(n){var o;"string"==typeof n&&(o=n);var i={message:o},r=Object.assign({},i,e,n);return new(t.extend(s))({el:document.createElement("div"),propsData:r})},success:function(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return this.open(Object.assign({},{message:t,type:"success"},e))},error:function(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return this.open(Object.assign({},{message:t,type:"error"},e))},info:function(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return this.open(Object.assign({},{message:t,type:"info"},e))},warning:function(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return this.open(Object.assign({},{message:t,type:"warning"},e))},default:function(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return this.open(Object.assign({},{message:t,type:"default"},e))}}};n(0);s.install=function(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},n=r(t,e);t.$toast=n,t.prototype.$toast=n};e.default=s}]).default});