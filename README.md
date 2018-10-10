# AstronomVM [![Build Status](https://travis-ci.org/AhmedRiahi/AstronomVM.svg?branch=master)](https://travis-ci.org/AhmedRiahi/AstronomVM) [![Sonar](https://sonarcloud.io/api/project_badges/measure?project=AhmedRiahi_AstronomVM&metric=alert_status)](https://sonarcloud.io/api/project_badges/measure?project=AhmedRiahi_AstronomVM&metric=alert_status)

AstronomVM is a platform that aims to provide the possibility to create, execute and deploy functional workflows.The main objective is to design workflows basing on pre-built components.

<h2>Project Structure</h2>
<ul>
  <li><b>AstronomKernel :</b> This module provide the implementation of the Core Engine that process workflow provided as input</li>
  <li><b>AstronomCore :</b> Domain module that provide data structures/definitions used by other modules</li> 
  <li><b>AstronomDesigner :</b> UI module</li> 
  <li><b>AstronomSimulator :</b> Basing on AstronomKernel, this module provide APIs to execute and simulate workflows' execution</li> 
  <li><b>AstronomAgent :</b> AstronomAgent is similar to AstronomSimulator, it aims to be deployed in order to execute workflows</li>
  <li><b>FunctionalRepositoryService :</b> This module implement the Functional Models CRUD operations</li>
  <li><b>AstronomComponent :</b> This is the abstract specification of Astronom components</li> 
  <li><b>List of Components</b>
    <ul>
      <li>CSVFileLoaderComponent</li>
      <li>RowFilterComponent</li>
      <li>TextFileOutputComponent</li>
      <li>FunctionalModelMapperComponent</li>
    </ul>
   </li>
</ul>

<h2>Platform Architecture</h2>

![Screenshot](AstronomVM.png)

<h2>Documentation</h2>
To be done

<h2>Installation instructions</h2>
<h3>Prerequisites</h3>
To be done
<h3>Installation</h3>
To be done

<h2>Contributing</h2>
All contributions are welcom :)
