# 1.Create poml.jar
## Create poml.jar using existing pom.xml.
mvn clean package -DskipTests=true

## Convert pom.poml to pom.xml.
./dist/poml

## Create poml.jar using generated pom.xml.
mvn clean package


# 2.Create zip.
## Get version and define file name.
$VER=./dist/poml -v
$POML_NAME="poml-"+$VER

## Create dirs.
$SRC_DIR=$POML_NAME
$DST_DIR="archive"
if(Test-Path $SRC_DIR){
  Remove-Item $SRC_DIR -Recurse
}
if(Test-Path $DST_DIR){
  Remove-Item $DST_DIR -Recurse
}
New-Item $SRC_DIR -itemType Directory
New-Item $DST_DIR -itemType Directory

## Copy jar,sh,cmd to src.
Copy-Item dist/poml.jar $SRC_DIR/
Copy-Item dist/poml $SRC_DIR/
Copy-Item dist/poml.cmd $SRC_DIR/

## Zip src to dst.
Write-Output "[POML:INFO] Create zip."
Compress-Archive -Path $SRC_DIR/* -DestinationPath $DST_DIR/$POML_NAME.zip

Remove-Item $SRC_DIR -Recurse
