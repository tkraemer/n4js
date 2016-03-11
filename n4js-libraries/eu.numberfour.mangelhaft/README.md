Mangelhaft: An xUnit Testing Framework for N4JS.

For more information about N4JS, please refer to the official [project home](https://numberfour.github.io/n4js).

# mangelhaft testing framework

## Overview
Mangelhaft is a testing library for n4js projects. It runs all test groups asynchronously whether or not they are @Async. Within a group the tests are run in order. 

## Anatomy Of a Test
A group of tests is a class which has at least 1 `@Test` annotation.

Within that class you may annotate methods with `@BeforeAll`, `@Before`, `@After`, `@AfterAll`, or `@Test`. This will register that method as being a 'test method'. These will get called in the order described below. In addition any of these methods can be given a description with `@Description("any descriptive text")`. The description will be optionally used in the test report.

## Test Methods
All test methods take a description and a function with no arguments. Within that function is where the action (assert statements, setup etc) goes. You may use `async`/`await` with any of them if there is deferred action. If a promise is returned, you can be assured that the next type of test method will not be called until it has completed. There can be multiple of any method types.

The order of execution is:

1. beforeAlls
2. For Each `@Test`
    1. befores
    2. test
    3. afters
3. afterAlls

WARNING: the order that the individual non-test methods are called in is random ie all `@BeforeAll`'s are run before everything else but in any order. However, within a group, the tests are run serially (so a test with its befores and afters completes before the next one starts, even if the test or its test methods are async).

### `@BeforeAll`
executed before any of the other test function types. Should be used for top level testing setup

### `@AfterAll`
executed after all of the other test function types. Should be used for overall testGroup cleanup

### `@Before`
run before each test. used to initialize test objects etc

### `@After`
run after each test. used to cleanup test objects etc in preparation for the next test

### `@Test`
the test blocks. Any testing code or Assert statements should go within these functions.

## Complete example test listing
```typescript
import Assert from "n4/mangel/assert/Assert";

class Rectangle {
    length: number = 0;
    width: number = 0;
    area: number = 0;
    disposed: boolean = false;
    constructor(length: number, width: number) {
        this.length = length;
        this.width = width;
        this.area = length * width;
    }
    async public dispose() {
        await this.disposed = /*async operation*/ true;
    }
}

export public class TestRectangles with TestGroup {
    private Rectangle currentRect;
    private Array<?> icons;
    private environment;
    @BeforeAll
    getEnvironment() {
        this.environment = {}; 
    }
    @BeforeAll
    loadIcons() {
        this.icons = ["icon1", "icon2", "icon3"];
    }
    @AfterAll
    releaseEnvironment() {
        this.environment = null; 
    }
    @AfterAll
    freeIcons() {
        this.icons = null; 
    }
    @Before
    createRectangleInstanceForTest() {
        this.currentRect = new Rectangle(2, 3);
    }
    @After
    disposeTestRectangleIfNeeded() {
        if (!this.currentRect.disposed) {
            this.currentRect.dispose();
        }
        this.currentRect = null;
    }
    @Test
    areaIsSet(){
        Assert.isTrue(this.currentRect.area != 0
            , "rectangle's area has been set")
    }
    @Test
    areaIsSetCorrectly(){
        Assert.strictEqual(this.currentRect.area, 6
            , "rectangle's area has been set Correctly")
    }
    @Test
    iconsLoaded() {
        Assert.deepEqual(this.icons, ["icon1", "icon2", "icon3"], 
        "icons equivalent to expected");  
    }
    @Test
    @Description("Dispose method should be called and object should be disposed")
    async disposeMethodShouldWork() {
        Assert.isFalse(this.currentRect.disposed, 
            "initial rectangle not in disposed state");
        await this.currentRect.dispose();
        Assert.isTrue(this.currentRect.disposed, 
            "dispose function has been called");
   }
} 
```

## License

Copyright (c) 2016 NumberFour AG.
[EPL-1.0](http://www.eclipse.org/legal/epl-v10.html)
