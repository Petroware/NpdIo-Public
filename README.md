# NPD I/O - NPD access library #

The NPD module is a library for providing the [NPD fact pages](http://factpages.npd.no/factpages/)
information through a clean and simple Java API.

![NPD I/O library](https://petroware.no/images/NpdIoBox.250.png)

The library reads information from CSV pages whitin the fact pages web site.
The exact location (URL) of the CSV pages are not built into NPD I/O in order
to stay robust for URL changes. If the CSV structure change however, Log I/O
must adapt accordingly and be rebuilt. Such changes are infrequent, but typically
announced by the NPD well ahead.


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
