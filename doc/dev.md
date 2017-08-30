## Task List for Release
- Dev (coding, test)
- Update document
  - readme.md
  - poml/doc/*
- Pull request
  - merge
- Create archive (tar, zip)
  - pull (@master)
  - exec archive.sh
  - check archives
- Release (GitHub)
  - write changelog
  - upload tar, zip
- Notification
  - twitter
  - blogger (POML Blog)
  - hatena


## Process to create archive (tar, zip)
### 1. Get sources of POML
#### git clone (1st time)
```
~$ git clone https://github.com/mamorum/poml.git
```

#### git pull (2nd+)
```
~$ cd poml
~$ git pull
```

### 2. Execut shell
```
~$ cd poml
~/poml$ ./archive.sh
```

### 3. Check archive
```
~/poml$ ls -l archive
total 64
-rw-rw-r-- 1 vagrant vagrant 32233 Feb 24 00:03 poml-0.2.2.tar.gz
-rw-rw-r-- 1 vagrant vagrant 32545 Feb 24 00:03 poml-0.2.2.zip
```


## Env to create archive (tar, zip)
### OS
[ubuntu/trusty64](https://atlas.hashicorp.com/ubuntu/boxes/trusty64) (vagrant box)

### Installed Software
- jdk8
  -> http://web-dev.hatenablog.com/entry/java/jdk/ubuntu-install
- maven
  -> http://web-dev.hatenablog.com/entry/maven/centos-install
- git: `$ sudo apt-get install git`
- zip, unzip: `$ sudo apt-get install zip unzip`
- nkf: `$ sudo apt-get install nkf` 
