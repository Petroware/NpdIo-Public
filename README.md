# NPD I/O - NPD access library #

The [Norwegian Petroleum Directorate (NPD)](http://npd.no)
maintains a comprehensive database of the activities in the North Sea.
This is public real-time information updated on a daily basis and
made public through the [NPD fact pages](http://factpages.npd.no/factpages/).

The NPD database contains information on more than 7500 wells, 4000 surveys,
120 fields, 1000 facilities, 700 companies, 1200 licenses and more.

 ![NPD I/O library](https://petroware.no/images/NpdIoBox.250.png)

NPD I/O is a Java library for accessing this information through a clean and
simple to use Java API.


### Setup ###

Capture the NPD I/O code to local disk by:

```
$ git clone https://github.com/Petroware/NpdIo.git
```


### Dependencies ###

NPD I/O has no external dependenies.


### Building NPD I/O ###

NPD I/O can be built from its root folder by

```
$ make clean
$ make
$ make jar
```

The NPD I/O delivery will be the `./lib/NpdIo.jar` file.

Building with make requires the make module of the tools repository.


### Creating Javadoc ###

Public Javadoc: https://petroware.no/npdio/javadoc/index.html

Javadoc can be created locally by:

```
$ make javadoc
```

Entry point will be `./docs/index.html`.

Note the `./overview.html` page that becomes part of the Javadoc.

Note also that there is some Javadoc configuration in `./Makefile`. The Javadoc is not
automatically date stamped. The Javadoc date (current month) is specified in the Makefile.
