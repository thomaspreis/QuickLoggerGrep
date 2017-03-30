# QuickLoggerGrep
### Quick Logger Grep 

It helps to quickly read log files from a source folder grepping it to a new file containing only the matched lines.

In the source test folder there is a class: net.thomaspreis.tools.qlg.QuickLoggerGrepTest, we need simply to define some items:
 
#### Origin path (where the full logs are located)
String basePath = "C:\\temp\\logs\\";
#### Target path (where the generated and grepped files will be created)
String targetPath = "C:\\temp\\logs\\grepped-logs\\";

#### Expressions to do the grep must be included
qlg.addExpression(QLGExpressionEnum.STRING_CONTAINS_IGNORE_CASE, "Log entry");
qlg.addExpression(QLGExpressionEnum.STRING_CONTAINS_EQUAL_CASE, "Log Entry Exact");

Just perform the "QuickLoggerGrepTest" and enjoy it!