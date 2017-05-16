Axiom is updated to consume the CMS apis.
CMS apis were using SOAP 1.2 namespace but were complaint with SOAP 1.1.
Hence Axiom is updated to ignore few of the checks, to work with CMS. This is a very bad way to fix !!!

How to build this project:

cd {ROOT_OF_THIS_project}
mvn -Dmaven.test.skip=true -Dmaven.javadoc.skip=true clean  package

Where to find the jar:
ls -l ${ROOT_OF_THIS_PROJECT}/axiom-all/target/axiom-all-1.2.20.jar


Add the above jar file into your project dependancies.


======================================================
Apache Axiom ${project.version} (${build_date})

http://ws.apache.org/axiom/
------------------------------------------------------

___________________
Documentation
===================
 
Documentation can be found within this release and in the main site.

___________________
Support
===================
 
Any problem with this release can be reported to Apache Web Services mailing lists. 
If you are sending an email to the mailing list make sure to add the [Axiom] prefix to the subject.

Mailing list subscription:

users-subscribe@ws.apache.org
dev-subscribe@ws.apache.org

Thank you for using Axiom!

The Apache Axiom Team.
