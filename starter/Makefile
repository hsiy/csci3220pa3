# if compiling the code from the command line, consider using a build tool like "make"
# 
# "make" is a utility that is on most Linux/Mac machines
# running "make all" will compile java files into the list of class files given below
# 


OBJ := BinaryTreeNode.class BinarySearchTree.class GraphvizView.class INode.class ITree.class SampleMain.class

all: $(OBJ)


clean:
	rm *.class


$(OBJ): %.class : %.java
	javac $<

