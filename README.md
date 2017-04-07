# tls1.2

this scripts is my reference on how i created certificates for a self-signed 
composed of root authority, subordinate or immediate authority, and a server and
client certificates for a 2 way authentication. 

this can also be use in a 1 way authentication.

i used tomcat8.5.13 on OSX. 

this is the setup for 1 and 2 way authentication in server.xml of conf/server.xml


	<!-- working config 1 way authentication-->
	<Connector port="8443" maxHttpHeaderSize="8192"
		maxThreads="150"
		enableLookups="false" disableUploadTimeout="true"
		acceptCount="100" scheme="https" secure="true"
		SSLEnabled="true"
		SSLCertificateFile="/Users/user/Desktop/folders/projs/refs/ca4/root/ca/intermediate/certs/tomcatserver.cert.pem"
		SSLPassword="123456"
		SSLCertificateKeyFile="/Users/user/Desktop/folders/projs/refs/ca4/root/ca/intermediate/private/tomcatserver.key.pem" 
		SSLProtocol="TLSv1.2"
		SSLCertificateChainFile="/Users/user/Desktop/folders/projs/refs/ca4/root/ca/intermediate/certs/root.sub.ca.chain.cert.pem"
	/>
	<!-- end, working config 1 way authentication-->

	<!-- working config 2 way authentication for java client, Safari, Chrome, Firefox browsers-->
    <Connector
          protocol = "org.apache.coyote.http11.Http11Nio2Protocol"
          port = "8443"
          SSLEnabled = "true"
          scheme = "https"
          secure = "true"
          clientAuth = "true"
          sslProtocol = "TLS"
          sslEnabledProtocols = "TLSv1.2"
          keystoreFile = "/Users/user/Desktop/folders/projs/refs/ca4/root/ca/tomcatserver.jks"
          keystorePass = "123456"
          truststoreFile="/Users/user/Desktop/folders/projs/refs/ca4/root/ca/tomcatservertruststore.jks"
          truststorePass="123456"
          sessionTimeout = "86400"
          sessionCacheSize = "10000"
    />
	<!-- end, working config 2 way authentication for java client-->

credits to https://jamielinux.com/docs/openssl-certificate-authority/create-the-root-pair.html#create-the-root-key
the only tutorial that has the complete information