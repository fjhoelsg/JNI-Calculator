# Main Target Name (JAR Name)
MAIN_TARGET_NAME="JNI Calculator.jar"

# C Library Name
C_TARGET_LIB_NAME=calc

# Directories
JAVA_SRC_DIR=src/main/java
C_SRC_DIR=src/main/c
BLD_DIR=build
BIN_DIR=bin

# Java Headers Directories
JAVA_INCLUDE=$(shell echo $$JAVA_HOME)/include

# Java Platform Dependant Header Directories
JAVA_PLATFORM_INCLUDE=$(shell \
	if test -d $(JAVA_INCLUDE)/linux;\
		then echo $(JAVA_INCLUDE)/linux; \
	fi;)

ifeq ($(strip $(JAVA_PLATFORM_INCLUDE)),)
	JAVA_PLATFORM_INCLUDE=$(shell \
		if test -d $(JAVA_INCLUDE)/darwin;\
			then echo $(JAVA_INCLUDE)/darwin; \
		fi;)
endif

ifeq ($(strip $(JAVA_PLATFORM_INCLUDE)),)
	JAVA_PLATFORM_INCLUDE=$(shell \
		if test -d $(JAVA_INCLUDE)/win32;\
			then echo $(JAVA_INCLUDE)/win32; \
		fi;)
endif

# C Compiler
CC=gcc

# Java Compiler
JC=javac

# Jar Utility
JAR=jar

# Java Entry Class
JAVA_ENTRY_CLASS=$(shell echo \
		$(patsubst $(JAVA_SRC_DIR)/%.java, %, \
			$(shell grep -HR -l -m 1 "public static void main" \
				$(JAVA_SRC_DIR))) | tr / .)

# Java Class Files
JAVA_CLASS_FILES=$(patsubst $(JAVA_SRC_DIR)/%.java, $(BLD_DIR)/%.class,\
	$(shell find $(JAVA_SRC_DIR) -name '*.java'))

# Main Target (JAR)
MAIN_TARGET=$(BIN_DIR)/$(MAIN_TARGET_NAME)

# C_LIB
C_TARGET_LIB_FILENAME=lib$(C_TARGET_LIB_NAME).so
C_TARGET_LIB=$(BLD_DIR)/$(C_TARGET_LIB_FILENAME)

# Default Target
default: $(MAIN_TARGET)

# Main Target
$(MAIN_TARGET): $(C_TARGET_LIB) $(JAVA_CLASS_FILES)
	@echo Creating jar file...
	$(shell mkdir -p $(BIN_DIR); \
			cd $(BLD_DIR); \
			$(JAR) cfe \
				"$$OLDPWD"/$(MAIN_TARGET) \
				$(JAVA_ENTRY_CLASS) \
				$(C_TARGET_LIB_FILENAME) \
				$(patsubst ./$(BLD_DIR)/%, %, \
					$(shell find . -name '*.class' | sed 's/\$$/\\$$/g')))

# C Library
$(C_TARGET_LIB):
	@mkdir -p $(BLD_DIR)
	@echo Creating C library...
	@$(CC) \
		-I$(JAVA_PLATFORM_INCLUDE) \
		-I$(JAVA_INCLUDE) \
		-shared \
		-fPIC \
		-o $(C_TARGET_LIB) \
		$(shell find $(C_SRC_DIR) -name '*.c')

# Build Class Files
$(BLD_DIR)/%.class:
	@mkdir -p $(BLD_DIR)
	@echo Compiling $(JAVA_SRC_DIR)/$*.java...
	@$(JC) \
		$(JFLAGS) \
		-sourcepath $(JAVA_SRC_DIR) \
		-d $(BLD_DIR) \
		$(JAVA_SRC_DIR)/$*.java

# Delete Compiled Object and Binary Files
clean:
	@echo Deleting binary files...
	@rm -rf $(BIN_DIR)
	@echo Deleting class files...
	@rm -rf $(BLD_DIR)

# Fake targets
.PHONY: default clean
