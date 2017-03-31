# QuickLoggerGrep
### Quick Logger Grep 

It helps to quickly read log files from a source folder grepping it to a new file containing only the matched lines.

In the source test folder there is a class: net.thomaspreis.tools.qlg.QuickLoggerGrepTest, we need simply to define some items:
 
#### Origin path (where the complete logs files are located)
String basePath = "C:\\temp\\logs\\";
#### Target path (where the generated and grepped files will be created)
String targetPath = "C:\\temp\\logs\\grepped-logs\\";

#### Expressions to do the grep must be included
// Expression set - Operator AND to be used between the expressions

QLGExpressionSet expSet1 = new QLGExpressionSet(QLGExpressionOperatorEnum.AND);

// Expression 1 - Line contains the String 'cache' - ignore case

expSet1.add(QLGExpressionEnum.STRING_CONTAINS_IGNORE_CASE, "cache");

// Expression 2 - Line contains the String '[INFO]' - case sensitive

expSet1.add(QLGExpressionEnum.STRING_CONTAINS_EQUAL_CASE, "[INFO]");

// Expression 3 - Regular expression (date)

expSet1.add(QLGExpressionEnum.REGULAR_EXPRESSION, "\\d{4}-\\d{2}-\\d{2}");

Just perform the "QuickLoggerGrepTest" and enjoy it!