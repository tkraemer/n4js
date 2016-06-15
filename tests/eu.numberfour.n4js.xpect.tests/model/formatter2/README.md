
# This folder contains test cases targeting the formatter.



## Problematic cases

Auto-Wrapping is currently behaving strange in some ways.


*  In file  [x_annoations....](x_annotations_long_wrapping_problem.n4js.xt) one test is marked with a FIXME.
    
    It wraps in the wrong position.
		
	The warp is inserted in front of the "extends" keyword. But the auto-wrap should not be triggered in that line. The actual trigger is 
	in the line with the very long @Description annotation.
	 	
*  In file [autowrap.n4js.xt](autowrap.n4js.xt) the first two tests (also marked as FIXMEs) show a double-auto-wrap behavior, where a long line is split into three instead of two lines.
 
*  In file [wishes01Before.n4js.xt](wishes01Before.n4js.xt) there is a single huge test (currently with FIXME) showing the expectation

*  In file [expressions2.n4js.xt](expressions2.n4js.xt) there is a FIXME disable the first of two tests. NOTE if the second test is completely removed from the file, the first test 
	passes. --> first test is affected by the following lines of code not under test. 

