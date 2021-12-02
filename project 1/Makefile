run: compile
	java FrontEndCode

compile: test
	javac FrontEndCode.java
	javac Backend.java
	javac MovieDataReader.java

test: testData testBackend testFrontend clean

testFrontend:
	javac FrontEndCode.java
	javac TestFrontend.java
	java TestFrontend

testBackend:
	javac Backend.java
	javac TestBackend.java
	java TestBackend

testData:
	javac MovieDataReader.java
	javac MovieDataReaderTester.java
	java MovieDataReaderTester
clean:
	$(RM) *.class
