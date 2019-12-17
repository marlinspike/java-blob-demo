## Purpose
Demonstrates uploads and listing of files using a Connection made either via a SAS Token or an Account Key

## Note
Place files to upload in the **data** folder. Go here for [Sample Files of Varying sizes!](https://www.thinkbroadband.com/download).

## Usage with a SAS Token
Note the **S** parameter is the second argument, to signify a SAS Token is being used

mvn exec:java -Dexec.mainClass="com.blobs.demo.App" -Dexec.cleanupDaemonThreads=false -Dexec.args="<SAS_TOKEN> S"

## Usage with an Account Key
Note the **A** parameter is the second argument, to signify an Account Key is being used

mvn exec:java -Dexec.mainClass="com.blobs.demo.App" -Dexec.cleanupDaemonThreads=false -Dexec.args="<ACCOUNT_KEY> A"