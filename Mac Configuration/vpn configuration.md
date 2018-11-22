## Vpn Configuration
---
1. l2tp setup
+ kvm
    >wget --no-check-certificate https://raw.githubusercontent.com/teddysun/across/master/l2tp.sh
chmod +x l2tp.sh
./l2tp.sh

+ openVZ
    >wget https://git.io/vpn -O openvpn-install.sh && bash openvpn-install.sh
2. shadowsocks
2. wget --no-check-certificate -O shadowsocks-all.sh https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocks-all.sh
chmod +x shadowsocks-all.sh
./shadowsocks-all.sh 2>&1 | tee shadowsocks-all.log

---------------------
wget --no-check-certificate https://raw.githubusercontent.com/teddysun/across/master/l2tp.sh
chmod +x l2tp.sh
./l2tp.sh
