# QuickLoggerGrep
### Quick Logger Grep 

It helps to quickly read log files from a source folder grepping it to a new file containing only the matched lines.

In the source test folder there is a class: net.thomaspreis.tools.qlg.QuickLoggerGrepTest, we need simply to define some items:
 
#### Origin path (where the complete logs files are located)
String basePath = "C:\\temp\\logs\\";
#### Target path (where the generated and grepped files will be created)
String targetPath = "C:\\temp\\logs\\grepped-logs\\";

#### Expressions to do the grep must be included
// String indexof ignore case
quickLoggerGrep.addExpression(QLGExpressionEnum.STRING_CONTAINS_IGNORE_CASE, "GROUp cache for application");
// String indexof equals case
quickLoggerGrep.addExpression(QLGExpressionEnum.STRING_CONTAINS_EQUAL_CASE, "FILESYSTEM");
// Regular Expression (date)
quickLoggerGrep.addExpression(QLGExpressionEnum.REGULAR_EXPRESSION, "\\d{4}-\\d{2}-\\d{2}");

Just perform the "QuickLoggerGrepTest" and enjoy it!