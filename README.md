<!---
 Licensed to the Apache Software Foundation (ASF) under one or more
 contributor license agreements.  See the NOTICE file distributed with
 this work for additional information regarding copyright ownership.
 The ASF licenses this file to You under the Apache License, Version 2.0
 (the "License"); you may not use this file except in compliance with
 the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
[Apache Maven Hocon Extension](https://maven.apache.org/extensions/maven-xinclude-extension/)
==================================

[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)](https://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/org.apache.maven.extensions/maven-xinclude-extension.svg?label=Maven%20Central)](https://search.maven.org/artifact/org.apache.maven.extensions/maven-xinclude-extension)

This project provides a Hocon POM parser extension for Maven 4. It allows POMs to 
be written with the [Hocon](https://github.com/lightbend/config/blob/master/HOCON.md)
syntax, which is a superset of the [JSON](https://json.org/) syntax.

License
-------
This code is under the [Apache License, Version 2.0, January 2004][./LICENSE].

See the [`NOTICE`](./NOTICE) file for required notices and attributions.

Usage
-----
To use this extension, the following declaration needs to be done in your `${rootDirectory}/.mvn/extensions.xml`:
```
<extensions xmlns="http://maven.apache.org/EXTENSIONS/1.2.0">
    <extension>
        <groupId>org.apache.maven.extensions</groupId>
        <artifactId>maven-hocon-extension</artifactId>
        <version>@project.version@</version>
    </extension>
</extensions>
```
This allows defining a POM using Hocon syntax:
```
modelVersion = 4.1.0
parent {
    groupId = org.apache.maven.hocon.its
    artifactId = parent
    version = 1.0.0-SNAPSHOT
}
artifactId = test

properties = {
  "my.property" = foo
  pluginVersion = 3.9
}

dependencies = [
    # just add one dummy dependency
    "com.typesafe:config:1.4.2"
]
```
