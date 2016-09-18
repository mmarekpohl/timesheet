# Timesheet

* Why? To avoid manual timesheet creation in the end of the month.
* Log work in jira tempo every day. In the end of the month generate google doc timesheet.

## How to start?

* Export timesheet from [Jira Tempo](https://jira.4finance.net/secure/TempoUserBoard!timesheet.jspa).
  * Click top right corner button "Export" -> "Export to excel".
    * Optional: If you want to check the format: see `timesheet/src/test/resources/in/in.csv`
  * Convert to CSV by cell processor using comma separator and double quot (Libre office default).
* Clone this repo & Run `mvn clean install exec:java -Dexec.mainClass=org.timesheet.Main -Dexec.args="in/in.csv out/out.csv"`.
* Open `out/out.csv` by cell processor & paste into google doc timesheet. 
