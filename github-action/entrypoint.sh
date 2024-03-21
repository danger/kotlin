#! /bin/bash

set -e

dangerFile=$1
runMode=$2
jobId=$3
args=$4

echo "Danger JS version:"
danger --version
echo "Danger Kotlin version:"
danger-koltin --version

if [ -f "$dangerFile"]; then
  danger-kotlin $runMode --dangerfile="$dangerFile" --id="$jobId" $args
  exit 0
else
  echo "Danger file $dangerFile does not exist."
  exit 1
fi
