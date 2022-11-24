# Testla Screenplay-Playwright

[![](https://jitpack.io/v/testla-project/testla-screenplay-playwright-java.svg)](https://jitpack.io/#testla-project/testla-screenplay-playwright-java)
[![](https://jitci.com/gh/testla-project/testla-screenplay-playwright-java/svg)](https://jitci.com/gh/testla-project/testla-screenplay-playwright-java)

## Introduction

The testla project is a collection of tools of different tools to help in the QA automation process.
This package uses the [Testla screenplay core package]() to implement the screenplay pattern for Playwright.

## How to use this package?

Testla Screenplay-Playwright comes with abilities, question and actions to browse user interfaces and querying APIs.

### BrowseTheWeb Ability

This ability enables the actor to interact with the browser and browse web user interfaces.

#### using(Page page)

Initializes the Ability by passing a Playwright Page object.

#### as(Actor actor)

Use the Ability as an Actor. Required by Actions to get access to the ability functions. Examples can be found below.

#### addCookies(List<Cookie> cookies)

Add cookies into this browser context. All pages within this context will have these cookies installed. Cookies can be obtained via BrowseTheWeb.getCookies([urls]).

```java
BrowseTheWeb.as(actor).addCookies(Arrays.asList(cookieObject1, cookieObject2));
```

#### checkBox(String selector, SelectorOptions options)

Check the specified checkbox.

```java
// simple call with just selector
BrowseTheWeb.as(actor).checkBox("mySelector");
// or with options
BrowseTheWeb.as(actor).checkBox("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

#### clearCookies()

Clear the browser context cookies.

```java
BrowseTheWeb.as(actor).clearCookies();
```

#### click(String selector, SelectorOptions options)

Click the element specified by the selector.

```java
// simple call with just selector
BrowseTheWeb.as(actor).click("mySelector");
// or with options
BrowseTheWeb.as(actor).click("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

#### dblclick(String selector, SelectorOptions options)

Double Click the element specified by the selector.

```java
// simple call with just selector
BrowseTheWeb.as(actor).dblclick("mySelector");
// or with options
BrowseTheWeb.as(actor).dblclick("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

#### dragAndDrop(String sourceSelector, String targetSelector, SelectorOptions sourceOptions, SelectorOptions targetOptions)

Drag the specified source element to the specified target element and drop it.

```java
// simple call with just source and target selector
BrowseTheWeb.as(actor).dragAndDrop("sourceSelector", "targetSelector");
// or with options
BrowseTheWeb.as(actor).dragAndDrop("sourceSelector", "targetSelector",
    new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)),
    new SelectorOptions("myText", null, new SubSelector("mySubSelector", null))
);
```

#### fill(String selector, String input, SelectorOptions options)

Fill the element specified by the selector with the given input.

```java
// simple call with just selector and input value
BrowseTheWeb.as(actor).fill("mySelector", "myInput");
// or with options
BrowseTheWeb.as(actor).fill("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

#### getCookies(String urls)

Get the cookies of the current browser context. If no URLs are specified, this method returns all cookies. If URLs are specified, only cookies that affect those URLs are returned.

```java
// get all cookies
BrowseTheWeb.as(actor).getCookies();
// get cookies for one single domain
BrowseTheWeb.as(actor).getCookies("https:www.myapp.com");
// get cookies for multiple domains
BrowseTheWeb.as(actor).getCookies(Arrays.asList("https:www.myapp.com", "https:www.another-app.com"));
```

#### getLocalStorageItem(String key)

Get a local storage item specified by the given key.

```java
BrowseTheWeb.as(actor).getLocalStorageItem("some key");
```

#### getSessionStorageItem(String key)

Get a session storage item specified by given key.

```java
BrowseTheWeb.as(actor).getSessionStorageItem("some key");
```

#### navigate(String url)

Use the page to navigate to the specified url.

```java
BrowseTheWeb.as(actor).navigate("myURL");
```

#### hover(String selector, SelectorOptions options, List<KeyboardModifier> modifiers)

Use the page mouse to hover over the specified element.

```java
// simple call with just selector
BrowseTheWeb.as(actor).hover("mySelector");
// or with options
BrowseTheWeb.as(actor).hover("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)), Arrays.asList("Alt", "Shift"));
```

#### checkEnabledState(String selector, String mode, SelectorOptions options)

Verify if a locator on the page is enabled or disabled.

```java
// simple call with just selector
BrowseTheWeb.as(actor).checkEnabledState("mySelector", 'enabled");
// or with options
BrowseTheWeb.as(actor).checkEnabledState("mySelector", 'disabled', new SelectorOptions("myText", 10.0, new SubSelector("mySubSelector", null)));
```

#### checkVisibilityState(String selector, String mode, SelectorOptions options)

Verify if a locator on the page is visible.

```java
// simple call with just selector
BrowseTheWeb.as(actor).checkVisibilityState("mySelector", 'visible");
// or with options
BrowseTheWeb.as(actor).checkVisibilityState("mySelector", 'hidden', new SelectorOptions("myText", 10.0, new SubSelector("mySubSelector", null)));
```

#### press(String keys)

Press the specified key(s) on the keyboard.

```java
// Press a single button
BrowseTheWeb.as(actor).press("A");
// or multiple buttons
BrowseTheWeb.as(actor).press("Control+A");
```

#### removeLocalStorageItem(String key)

Delete a local storage item, if a key/value pair with the given key exists.

```java
BrowseTheWeb.as(actor).removeLocalStorageItem("some key");
```

#### removeSessionStorageItem(String key)

Delete a session storage item, if a key/value pair with the given key exists.

```java
BrowseTheWeb.as(actor).removeSessionStorageItem("some key");
```

#### setLocalStorageItem(String key, Object any)

Set a local storage item identified by the given key + value, creating a new key/value pair if none existed for key previously.

```java
BrowseTheWeb.as(actor).setLocalStorageItem("some key", 'some value");
```

#### setSessionStorageItem(String key, Object any)

Set a session storage item identified by the given key + value, creating a new key/value pair if none existed for key previously.

```java
BrowseTheWeb.as(actor).setSessionStorageItem("some key", 'some value");
```

#### type(String selector, String input, SelectorOptions options)

Type the given input into the element specified by the selector.

```java
// simple call with just selector and input value
BrowseTheWeb.as(actor).type("mySelector", "myInput");
// or with options
BrowseTheWeb.as(actor).type("mySelector", "myInput", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

#### waitForLoadState(LoadState status)

Wait for the specified loading state.

```java
BrowseTheWeb.as(actor).waitForLoadState("networkidle");
```

#### waitForSelector(String selector, SelectorOptions options)

Wait until the element of the specified selector exists.

```java
// simple call with just selector
BrowseTheWeb.as(actor).waitForSelector("mySelector");
// or with options
BrowseTheWeb.as(actor).waitForSelector("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

### Web Actions

#### Add.cookies(List<Cookie> cookies)

Add the specified cookies.

```java
Add.cookies(Arrays.asList(cookieObject1, cookieObject2));
```

#### Check.element(String selector, SelectorOptions options)

Checks a checkbox

```java
// simple call with just selector
Check.element("mySelector");
// or with options
Check.element("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```
#### Clear.cookies()

Clear all browser cookies.

```java
Clear.cookies();
```

#### Click.on(String selector, SelectorOptions options)

Click an element

```java
// simple call with just selector
Click.on("mySelector");
// or with options
Click.on("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

#### DoubleClick.on(String selector, SelectorOptions options)

Doubleclick an element

```java
// simple call with just selector
DoubleClick.on("mySelector");
// or with options
DoubleClick.on("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

#### DragAndDrop.execute(String sourceSelector, String targetSelector, SelectorOptions sourceOptions, SelectorOptions targetOptions)

Drag an element specified by the source selector and drop it at the target selector

```java
// simple call with just selector
DragAndDrop.execute("sourceSelector", "targetSelector");
// or with options
DragAndDrop.execute("sourceSelector", "targetSelector", 
    new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)),
    new SelectorOptions("myText", null, new SubSelector("mySubSelector", null))
);
```

#### Fill.in(String selector, String value, SelectorOptions options)

Fill a given string into an input element

```java
// simple call with just selector
Fill.in("mySelector", "myInput");
// or with options
Fill.in("mySelector", "myInput", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

#### Get.cookies(String url)

Get the specified cookies. If no urls are speciefied, get all cookies.

```java
// get all cookies
Get.cookies();
// get cookies for a single domain
Get.cookies("https://www.myapp.com");
// get cookies for multiple domains
Get.cookies(Arrays.asList("https://www.myapp.com", "https://www.another-app.com"));
```

#### Get.localStorageItem(String key)

Get a local storage item.

```java
Get.localStorageItem("some key");
```

#### Get.sessionStorageItem(String key)

Get a session storage item.

```java
Get.sessionStorageItem("some key");
```

#### Hover.over(String selector, SelectorOptions options, List<KeyboardModifiers> modifiers)

Hover an element

```java
// simple call with just selector
Hover.over("mySelector");
// or with options
Hover.over("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)), Arrays.asList("Alt", "Shift")); 
```

#### Press.key(String keys)

Press key(s) on the keyboard

```java
// single key
Press.key("A");
// multiple keys
Press.key("Control+A")
```

#### Navigate.to(String url)

Use the browser page to navigate to a specified url

```java
Navigate.to("myUrl");
```

#### Remove.localStorageItem(String key)

Remove a local storage item, if a key/value pair with the given key exists.

```java
Remove.localStorageItem("some key");
```

#### Remove.sessionStorageItem(String key)

Remove a session storage item, if a key/value pair with the given key exists.

```java
Remove.sessionStorageItem("some key");
```

#### Set.localStorageItem(String key, Object any)

Set a local storage item identified by the given key + value, creating a new key/value pair if none existed for key previously.

```java
Set.localStorageItem("some key", "some value");
```

#### Set.sessionStorageItem(String key, Object any)

Set a session storage item identified by the given key + value, creating a new key/value pair if none existed for key previously.

```java
Set.sessionStorageItem("some key", "some value");
```

#### Type.in(String selector, String value, SelectorOptions options)

Type a given string into an input element

```java
// simple call with just selector
Type.in("mySelector", "myInput");
// or with options
Type.in("mySelector", "myInput", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

#### Wait.forLoadState(LoadState status)

Wait for a load state to be present

```java
Wait.forLoadState("networkidle");
```

#### Wait.forSelector(String selector, SelectorOptions options)

Wait for an element to be present

```java
// simple call with just selector
Wait.forSelector("mySelector");
// or with options
Wait.forSelector("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

### UseAPI Ability

This ability enables the actor to interact with APIs by sending http requests.

#### as(Actor actor)

Use the Ability as an Actor. Used by Actions to get access to the ability functions. Examples can be found below.

#### using(APIRequestContext requestContext)

Initialize this Ability by passing an already existing Playwright APIRequestContext object.

#### sendRequest(RequestMethod method, String url, RequestOptions options, ResponseBodyFormat responseBodyFormat)

Send a request (GET, POST, PATCH, PUT, HEAD or DELETE) to the specified url. Headers, a desired format for the response's body and data can also be sent.

```java
UseApi.as(actor).sendRequest(
    RequestMethod.POST, "/items", 
    RequestOptions.create().
        setData("{ authorization: 'Bearer dfh.dasgeq65qg.eyjkhf' }").
        withHeaders(Map.of("title", "myHeader")), 
    ResponseBodyFormat.JSON
);
```

#### checkStatus(Response response, int status, String mode)

Verify if the given response's status is equal to the expected status.

```java
UseApi.as(actor).checkStatus(response, 200, "equal");
```

#### checkBody(Response response, Object body, String mode)

Verify if the given response's body is equal to the expected body. The check includes type safety.

```java
// json response
UseApi.as(actor).checkBody(response, "{ text: 'test' }", "equal");
// text response
UseApi.as(actor).checkBody(response, 'test', "unequal");
```

#### checkHeaders(Response response, Map<String, String> headers, String mode)

Verify if the given headers are included in the given response's headers.
If the header has a value !== undefined, both key and value will be checked. If a header has a value === undefined, only the key will be checked.

```java
// check only keys
UseApi.as(actor).checkHeaders(response, Map.of("contentType", null), "included");
// check key and value
UseApi.as(actor).checkHeaders(response, Map.of("contentType", "application/json"), "excluded");
```

#### checkDuration(Response response, Double duration, String mode)

Verify if the reponse (including receiving body) was received within a given duration.

```java
// check if response was received within 2s
UseApi.as(actor).checkDuration(response, 2000.0, "lessOrEqual");
```

### API Actions

#### Delete.from(String url)

Sends a DELETE request to the api. Optionally it is possible to chain definitions for headers and data as well as the expected response type.

```java
// simple request
Delete.from("https://my-fancy-url.com");
// with chained definitions
Delete.from("https://my-fancy-url.com")
    // add headers
    .withHeaders(Map.of("key", "value"))
    // add data
    .withData("{ key: value }")
    // define expected response format
    .withResponseFormat(ResponseBodyFormat.TEXT);
```

#### Get.from(String url)

Sends a GET request to the api. Optionally it is possible to chain definitions for headers as well as the expected response type.

```java
// simple request
Get.from("https://my-fancy-url.com");
// with chained definitions
Get.from("https://my-fancy-url.com")
    // add headers
    .withHeaders(Map.of("key", "value"))
    // define expected response format
    .withResponseFormat(ResponseBodyFormat.TEXT);
```

#### Head.from(String url)

Sends a HEAD request to the api. Optionally it is possible to chain definitions for headers as well as the expected response type.

```java
// simple request
Head.from("https://my-fancy-url.com");
// with chained definitions
Head.from("https://my-fancy-url.com")
    // add headers
    .withHeaders(Map.of("key", "value"))
    // define expected response format
    .withResponseFormat(ResponseBodyFormat.TEXT)
```

#### Patch.to(String url)

Sends a PATCH request to the api. Optionally it is possible to chain definitions for header sand data as well as the expected response type.

```java
// simple request
Patch.to("https://my-fancy-url.com");
// with chained definitions
Patch.to("https://my-fancy-url.com")
    // add headers
    .withHeaders(Map.of("key", "value"))
    // add data
    .withData("{ key: value }")
    // define expected response format
    .withResponseFormat(ResponseBodyFormat.TEXT)
```

#### Post.to(String url)

Sends a POST request to the api. Optionally it is possible to chain definitions for header sand data as well as the expected response type.

```java
// simple request
Post.to("https://my-fancy-url.com");
// with chained definitions
Post.to("https://my-fancy-url.com")
    // add headers
    .withHeaders(Map.of("key", "value"))
    // add data
    .withData("{ key: value }")
    // define expected response format
    .withResponseFormat(ResponseBodyFormat.TEXT)
```

#### Put.to(String url)

Sends a Put request to the api. Optionally it is possible to chain definitions for header sand data as well as the expected response type.

```java
// simple request
Put.to("https://my-fancy-url.com");
// with chained definitions
Put.to("https://my-fancy-url.com")
    // add headers
    .withHeaders(Map.of("key", "value"))
    // add data
    .withData("{ key: value }")
    // define expected response format
    .withResponseFormat(ResponseBodyFormat.TEXT)
```

### Generic Actions which do not require any particular Ability

#### Sleep.for(Long ms)

Pause the execution of further test steps for a given interval in milliseconds

```java
Sleep.for(5000);
```

### Available Web Questions

#### Element.toBe()

Checks if a condition is true.

#### Element.notToBe()

Checks if a condition is false.

#### Element.*.visible(String selector, SelectorOptions options)

Validates wether an element is visible. A mode operator must be prepended.

```java
// simple call with just selector
Element.toBe().visible("mySelector");
// or with options
Element.notToBe().visible("mySelector", new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

#### Element.*.enabled(String selector, SelectorOptions options)

Validates wether an element is enabled. A mode operator must be prepended.

```java
// simple call with just selector
Element.toBe().enabled("mySelector");
// or with options
Element.notToBe().enabled("mySelector",new SelectorOptions("myText", null, new SubSelector("mySubSelector", null)));
```

### Available Api Questions

#### Response.has()

Checks if a condition is true.

#### Response.hasNot()

Checks if a condition is false.

#### Response.*.statusCode(Response response, code: number)

Checks if the response has a given status code. A mode operator must be prepended.

```java
Response.has().statusCode(response, 200);
Response.hasNot().statusCode(response, 200);
```

#### Response.*.body(Response response, body: ResponseBodyType)

Checks if the response body equals a given body. A mode operator must be prepended.

```java
// json format
Response.has().body(response, { key: value });
// text format
Response.hasNot().body(response, "text");
```

#### Response.*.headers(Response response, headers: Headers)

Checks if the response has the given headers either by key (value to be set to undefined) or key/value lookup. A mode operator must be prepended.

```java
// only check for header presence by passing undefined as the value
Response.has().headers(response, Map.of("content-type", null));
// lookup for key/value combination to be present
Response.hasNot().headers(response,Map.of("content-type", "application/json"));
```

#### Response.*.beenReceivedWithin(Response response, duration: number)

Checks if the reponse (including receiving body) was received within a given duration. A mode operator must be prepended.

```java
// check if response was received within 2s
Response.has().beenReceivedWithin(response, 2000);
// check if response was not received within 2s
Response.hasNot().beenReceivedWithin(response, 2000);
```

### Group Actions into a Task

Tasks group actions into logical entities. Here is a task that uses the actions Navigate, Fill and Click from the web capabilities and Get from api capabilities.

```java
// file: ./task/Login.java


public class Login extends Task {
    public Object performAs(actor: Actor) {
        return actor.attemptsTo(
            Navigate.to("https://www.my-fancy-url.com"),
            Fill.with("#username", actor.states("username")),
            Fill.with("#password", actor.states("password")),
            Click.on("#login-button"),
            Get.from("https://www.my-fancy-url.com")
        );
    }

    public static Login toApp() {
        return new Login();
    }
}
```

### Initialize Actor with Abilities

Initialize an actor with abilities for later use in a test case.

```java
Actor actor = Actor.named("James")
            .with("username', 'John Doe")
            .with("password', 'MySecretPassword");
            .can(BrowseTheWeb.using(page))
            .can(UseAPI.using(request));
``` 

### Test case example

The final step is to define a test case using the Task defined above.

```java

// Example test case with Playwright
public void test() {

    Actor actor = Actor.named("James")
            .with("username', 'John Doe")
            .with("password', 'MySecretPassword");
            .can(BrowseTheWeb.using(page))
            .can(UseAPI.using(request));

    // Execute the task Login - as defined further above
    actor.attemptsTo(Login.toApp());

    // Check if the login was successful - use the status question from the web package
    actor.asks(Element.toBe().visible("#logged-in-indicator"));
}
```

Besides the existing actions, abilities and questions it is of course possible to define your own ones. How this is done, please refer to our [core package](https://www.npmjs.com/package/@testla/screenplay).

Since tasks, actions and questions return promises, we advise to make use of the [require-await](https://eslint.org/docs/rules/require-await) rule in case of using eslint. This will help to prevent unexpected behavior when forgetting to await tasks/actions or questions.
