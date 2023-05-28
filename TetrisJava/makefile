all: compile run clean

compile: driver1.java driver2.java
	@echo "-------------------------------------------"
	@echo "Compiling..."
	@javac tetris.java
	@javac tetromino.java
	@javac driver1.java
	@javac driver2.java

run: 
	@echo "-------------------------------------------"
	@echo "Running..."
	java driver1
	java driver2
	@echo "Completed."
	
clean:
	@echo "-------------------------------------------"
	@echo "Removing compiled files..."
	@del *.class
