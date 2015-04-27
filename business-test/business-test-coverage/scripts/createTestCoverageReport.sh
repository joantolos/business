#!/bin/sh
#Going to parent's folder
cd .. &&
cd .. &&
cd .. &&
#Cleaning the whole project
mvn clean compile &&
#Instrument Cobertura
cd business-test/business-test-coverage/scripts &&
ant instrument &&
#Going to parent's folder
cd .. &&
cd .. &&
cd .. &&
#Generating tests classes
mvn test &&
#Creating the Cobertura Report and cleaning Cobertura files
cd business-test/business-test-coverage/scripts &&
ant report &&
ant cleanCoberturaFiles