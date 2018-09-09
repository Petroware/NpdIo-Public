# NPD I/O - NPD access library

The [Norwegian Petroleum Directorate (NPD)](http://npd.no)
maintains a comprehensive database of the activities in the North Sea.
This is public real-time information updated on a daily basis and
made public through the [NPD fact pages](http://factpages.npd.no/factpages/).

The NPD database contains information on more than 7500 wells, 4000 surveys,
120 fields, 1000 facilities, 700 companies, 1200 licenses and more.

<img hspace="100" src="https://petroware.no/images/NpdIoBox.250.png">

NPD I/O is a Java library for accessing this information through a clean and
simple to use Java API. NPD I/O is available for Java (`NpdIo.jar`).
The library is lightweight (< 0.1MB) and self-contained; It has no external
dependencies. NPD I/O access data directly through HTTP - no login or
password is required.

NPD I/O web page: https://petroware.no/npdio.html


## Setup

Capture the NPD I/O code to local disk by:

```
$ git clone https://github.com/Petroware/NpdIo.git
```


## Dependencies

NPD I/O has no external dependenies.


## Javadoc

Public Javadoc: https://petroware.no/npdio/javadoc/index.html


## Programming examples

NPD I/O access data through HTTP. As the URL for each data may change over time,
they are nor built in to the library, but should be provided by the client application.

Below are the correct URLs for each data type:

| Data type                   | URL                    |
|-----------------------------|------------------------|
| ``NpdCompany``              | ``http://factpages.npd.no/ReportServer?/FactPages/TableView/company&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=92.221.121.112&CultureCode=en`` |
| ``NpdDevelopmentWellbore``  | ``http://factpages.npd.no/ReportServer?/FactPages/TableView/wellbore_development_all&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=80.239.106.206&CultureCode=en`` |
| ``NpdExplorationWellbore``  | ``http://factpages.npd.no/ReportServer?/FactPages/TableView/wellbore_exploration_all&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=80.239.106.206&CultureCode=en`` |
| ``NpdField``                | ``http://factpages.npd.no/ReportServer?/FactPages/TableView/field&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=92.221.121.112&CultureCode=en`` |
| ``NpdFixedFacility``        | ``http://factpages.npd.no/ReportServer?/FactPages/TableView/facility_fixed&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=92.221.121.112&CultureCode=en`` |
| ``NpdLicense``              | ``http://factpages.npd.no/ReportServer?/FactPages/TableView/licence&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=213.225.65.178&CultureCode=en`` |
| ``NpdMoveableFacility``     | ``http://factpages.npd.no/ReportServer?/FactPages/TableView/facility_moveable&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=92.221.121.112&CultureCode=en`` |
| ``NpdOtherWellbore``        | ``http://factpages.npd.no/ReportServer?/FactPages/TableView/wellbore_other_all&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=80.239.106.206&CultureCode=en`` |
| ``NpdProduction``           | ``http://factpages.npd.no/ReportServer?/FactPages/TableView/field_production_monthly&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=213.225.65.178&CultureCode=en`` |
| ``NpdSurvey``               | ``http://factpages.npd.no/ReportServer?/FactPages/TableView/survey&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=213.225.65.178&CultureCode=en`` |


<p>
Below are a few examples on how to access some of the main data types from the NPD database.

```java
   import no.petroware.npdio.field.*;
   import no.petroware.npdio.well.*;
   :

   //
   // Read development NPD wellbores
   //
   String url = "http://factpages.npd.no/..."; // Use NpdDevelopmentWellbore link from table above
   List<NpdWellbore> npdWellbores = NpdWellDataReader.readDevelopmentWellbores(url);

   // Loop over the wellbores and write to stdout
   for (NpdWellbore npdWellbore : npdWellbores)
     System.out.println(npdWellbore);

   :


   //
   // Read NPD fields
   //
   String url = "http://factpages.npd.no/..."; // Use NpdField link from table above
   List<NpdField> npdFields = NpdFieldReader.readFields(url);

   // Read production data for all fields
   String url = "http://factpages.npd.no/..."; // Use NpdProduction link from table above
   ProductionReader.readProduction(url, npdFields);

   // Loop over the fields and write oil production to stdout
   for (NpdField npdField : fields) {
     Production production = field.getProduction();

     for (Production.Entry productionEntry : production.getEntries()) {
       int year = productionEntry.getYear();
       int month = productionEntry.getMonth();

       double oilProduction = productionEntry.getOil();

       System.out.println("Oil production: " + year + "/" + month + ": " + oilProduction);
     }
   }

   :
```

Note that in an actual client implementation the reading process would better be
done asynchronous in threads. The NPD I/O library is all thread-safe.

NPD I/O includes Java implementations for the NPD data types _wellbore_, _license_,
_field_, _company_, _survey_, _facility_, _discovery_, _business arrangement area_,
_pipeline_ and _stratigraphy_.


# About Petroware

Petroware AS is a software company within the data management, data analytics,
petrophysics, geology and reservoir engineering domains.

Petroware creates highly advanced software components and end-user products that
acts as a research platform within software architecture and scalability, system design,
parallelism and multi-threading, user experience (UX) and usability analysis as well
as development methodologies and techniques.

**Petroware AS**<br>
Stavanger - Norway<br>
[https://petroware.no](https://petroware.no)<br>
info@petroware.no

