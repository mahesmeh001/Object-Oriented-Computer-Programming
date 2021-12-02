run: compile
	echo "WIP"

compile: test
	@echo "FIXME: *make compile* should compile the code for your project"

test: testData testBackend testFrontend

testFrontend:
	javac Frontend.java
	java Frontend

testBackend:
	javac BackEndCode.java
	java BackEndCode
testData:

clean:
	$(RM) *.class
