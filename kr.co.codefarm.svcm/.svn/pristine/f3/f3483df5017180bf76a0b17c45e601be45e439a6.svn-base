#!/bin/bash

# U-01 root 계정 원격 접속 제한. cat /etc/ssh/sshd_config
mkdir -p /etc/ssh
cat > /etc/ssh/sshd_config <<EOF
PermitRootLogin no
EOF

touch /etc/securetty && chmod 600 /etc/securetty

# U-02 패스워드 복잡성 설정. cat /etc/pam.d/common-password | grep enforce_for_root
apt -y install libpam-pwquality

cat >> /etc/pam.d/common-password <<EOF
password   requisite    pam_pwquality.so    enforce_for_root    retry=3    minlen=9 dcredit=-1 ucredit=-1 lcredit=-1 ocredit=-1
EOF

cat > /etc/security/pwquality.conf <<EOF
lcredit=-1
ucredit=-1
dcredit=-1
ocredit=-1
minlen=9
enforce_for_root
EOF

# U-03 계정 잠금 임계값 설정. 첫번째 단락 1번째줄. cat /etc/pam.d/common-auth | grep pam_tally2
echo 'auth required pam_tally2.so deny=5' | cat - /etc/pam.d/common-auth > temp && mv temp /etc/pam.d/common-auth


# U-04 패스워드 최대 사용 기간 설정. cat /etc/login.defs | grep PASS_MAX_DAYS
sed -i '/PASS_MAX_DAYS/d' /etc/login.defs
cat >> /etc/login.defs <<EOF
PASS_MAX_DAYS   90
EOF

# U-08 /etc/passwd 파일 소유자 및 권한 확인
chmod 400 /etc/shadow

# 현재 User의 최대 사용기간 적용
chage -M 90 root

# U-14 SUID, SGID, Sticky bit 설정 파일 점검
chmod -s /sbin/dump
chmod -s /usr/bin/lpq-lpd
chmod -s /usr/bin/newgrp
chmod -s /sbin/restore
chmod -s /usr/bin/lpr
chmod -s /usr/sbin/lpc
chmod -s /sbin/unix_chkpwd
chmod -s /usr/bin/lpr-lpd
chmod -s /usr/sbin/lpc-lpd
chmod -s /usr/bin/at
chmod -s /usr/bin/lprm
chmod -s /usr/sbin/traceroute
chmod -s /usr/bin/lpq
chmod -s /usr/bin/lprm-lpd

# U-18 접속 IP 및 포트 제한. cat /etc/hosts.deny
cat > /etc/hosts.deny <<EOF
ALL:ALL
EOF

chmod 600 /etc/hosts.deny

cat > /etc/hosts.allow <<EOF
EOF

chmod 600 /etc/hosts.allow
