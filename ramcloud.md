ubuntu 15.04 server amd64

update the sources.list

sudo sed -i -re 's/([a-z]{2}\.)?archive.ubuntu.com|security.ubuntu.com/old-releases.ubuntu.com/g' /etc/apt/sources.list


apt-get install default-jdk default-jre

apt-get install build-essential git-core libpcre3-dev protobuf-compiler libprotobuf-dev libcrypto++-dev libevent-dev libboost-all-dev libgtest-dev libzookeeper-mt-dev zookeeper libssl-dev

Install MLNX OFED driver: MLNX_OFED_LINUX-4.0-2.0.0.1-ubuntu15.04-x86_64.tgz

MLNX_DPDK=y ./scripts/dpdkBuild.sh 

make -j8 DPDK=yes DPDK_DIR=dpdk DEBUG=no


Note: You may need to delete `-Werror` in GNUmakefile.

1. Edit /etc/default/grub

This setting allocates 1GB * 16pages = 16GB hugepages on boot time.

    /etc/default/grub

GRUB_CMDLINE_LINUX_DEFAULT="default_hugepagesz=1G hugepagesz=1G hugepages=16"

2. Update GRUB

Run update-grub to apply the config to grub.

$ sudo update-grub

Once the hugepage memory is reserved, to make the memory available for DPDK use, perform the following steps:

mkdir -p /mnt/huge
mount -t hugetlbfs nodev /mnt/huge
chmod 777 /mnt/huge

p2p1      Link encap:Ethernet  HWaddr 7c:fe:90:c3:65:20  is MLNX NIC
rename7 is MLNX NIC


Try the latest RAMCloud to run DPDK.


When you run sudo in the u1 container, avoid asking sudo password, append this line to /etc/sudoers

# Allow members of group sudo to execute any command
%sudo	ALL=(ALL:ALL) ALL

your_user_name ALL = NOPASSWD : ALL

Note: When multiple entries match for a user, they are applied in order. Where there are multiple matches, the last match is used (which is not necessarily the most specific match).

To start the coordinator (DPDK):
make clean; make -j8 DPDK=yes DPDK_DIR=dpdk

sudo /home/hkucs/Migration/cheng/RAMCloud-Lab/obj.master/coordinator -C basic+udp:host=202.45.128.165,port=12246 -l NOTICE --dpdkPort 0 --configDir config

To start the server (DPDK):

sudo /home/hkucs/Migration/cheng/RAMCloud-Lab/obj.master/server -C basic+udp:host=202.45.128.165,port=12246 -L basic+dpdk:mac=7c:fe:90:91:4b:00 -r 0 -l NOTICE --clusterName __unnamed__  --preferredIndex 1 -t 4000 --dpdkPort 0 --configDir config -f /tmp/rc-backup

To start the client (DPDK):

sudo ./obj.master/apps/ClusterPerf -C basic+udp:host=202.45.128.165,port=12246 --dpdkPort=0 --numClients 1 --clientIndex 0 --seconds 10 --maxSessions 1 --size 100 basic --configDir config


To start the coordinator (TCP):
make clean; make -j8

sudo /home/hkucs/Migration/cheng/RAMCloud-Lab/obj.master/coordinator -C basic+udp:host=202.45.128.165,port=12246 -l NOTICE --configDir config

To start the server (TCP):
sudo /home/hkucs/Migration/cheng/RAMCloud-Lab/obj.master/server -C basic+udp:host=202.45.128.165,port=12246 -L tcp:host=10.22.1.4,port=12247 -r 0 -l NOTICE --clusterName __unnamed__  --preferredIndex 1 -t 4000 --configDir config -f /tmp/rc-backu

To start the client (DPDK):

sudo ./obj.master/apps/ClusterPerf -C basic+udp:host=202.45.128.165,port=12246 --numClients 1 --clientIndex 0 --seconds 10 --maxSessions 1 --size 100 basic --configDir config