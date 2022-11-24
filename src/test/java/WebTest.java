import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SameSiteAttribute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import testla.screenplay.actor.Actor;
import testla.web.SelectorOptions;
import testla.web.SubSelector;
import testla.web.abilities.BrowseTheWeb;
import testla.web.actions.*;
import testla.web.questions.Element;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// TODO: implement test for DoubleClick
// TODO: test different details between Fill and Type
class WebTest {

    // shared between all tests in this class.
    static Actor actor;
    static Page actorPage;


    @BeforeAll
    static void setup() {
        Page page = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
        actor = Actor.named("TestActor").can(BrowseTheWeb.using(page)).with("page", page);
    }

    @BeforeEach
    void getPage() {
        actorPage = (Page) actor.states("page");
    }

    @Test
    void NavigateTest() {
        actor.attemptsTo(
                Navigate.to("https://google.de")
        );
        // Page actorPage = (Page) actor.states("page");
        assertThat(actorPage).hasURL("https://www.google.de/");
    }

    @Test
    void DragAndDropTest() {
        actor.attemptsTo(
                Navigate.to("https://the-internet.herokuapp.com/drag_and_drop"),
                Wait.forLoadState(LoadState.NETWORKIDLE)
        );
        // before drag: Box A is on the Left
        assertThat(actorPage.locator("[id='column-a'] header")).hasText("A");

        // execute the drag
        actor.attemptsTo(
                DragAndDrop.execute("[id='column-a']", "[id='column-b']")
        );
        // after Drag: Box B is on the Left
        assertThat(actorPage.locator("[id='column-a'] header")).hasText("B");
    }

    @Test
    void CheckTest() {
        actor.attemptsTo(
                Navigate.to("https://the-internet.herokuapp.com/checkboxes"),
                Wait.forLoadState(LoadState.NETWORKIDLE),
                Check.element("//input[1]"),
                Check.element("//input[2]")
        );
        // assert that both boxes are checked
        assertThat(actorPage.locator("//input[1]")).isChecked();
        assertThat(actorPage.locator("//input[2]")).isChecked();
    }

    @Test
    void ClickTest() {
        actor.attemptsTo(
                Navigate.to("https://the-internet.herokuapp.com/add_remove_elements/"),
                Wait.forLoadState(LoadState.NETWORKIDLE)
        );
        // assert that there is no button before we add it with our Click
        assertThat(actorPage.locator("[class='added-manually']")).hasCount(0);

        actor.attemptsTo(
                Click.on("button", new SelectorOptions("Add Element", null, null))
        );
        // assert that the button is here after our Click
        assertThat(actorPage.locator("[class='added-manually']")).hasCount(1);
    }

    @Test
    void FillAndTypeTest() {
        actor.attemptsTo(
                Navigate.to("https://the-internet.herokuapp.com/login"),
                Wait.forLoadState(LoadState.NETWORKIDLE),
                Fill.in("[id='username']", "tomsmith"),
                Type.in("[id='password']", "SuperSecretPassword!"),
                Click.on("[class='radius']"),
                Wait.forLoadState(LoadState.NETWORKIDLE)
        );
        // assert that the login worked
        assertThat(actorPage).hasURL("https://the-internet.herokuapp.com/secure");
    }

    @Test
    void HoverTest() {
        actor.attemptsTo(
                Navigate.to("https://the-internet.herokuapp.com/hovers"),
                Wait.forLoadState(LoadState.NETWORKIDLE)
        );
        // assert that there is no info before the hover
        assertThat(actorPage.locator("[href='/users/1']")).not().isVisible();

        actor.attemptsTo(
                Hover.over("div.figure:nth-child(3) > img:nth-child(1)")
        );
        // assert that the info is now visible after hover
        assertThat(actorPage.locator("[href='/users/1']")).isVisible();
    }

    @Test
    void PressTest() {
        actor.attemptsTo(
                Navigate.to("https://the-internet.herokuapp.com/key_presses"),
                Wait.forLoadState(LoadState.NETWORKIDLE)
        );
        // assert that there is nothing in the result box
        assertThat(actorPage.locator("[id='result']")).hasText("");

        actor.attemptsTo(
                Click.on("[id='target']"),
                Press.key("a")
        );
        // assert that the pressed button was recognized
        assertThat(actorPage.locator("[id='result']")).hasText("You entered: A");
    }

    @Test
    void WaitAndRecursiveLocatorTest() {
        actor.attemptsTo(
                Navigate.to("https://the-internet.herokuapp.com/tables"),
                Wait.forLoadState(LoadState.NETWORKIDLE),

                Wait.forSelector("[id='table1']", new SelectorOptions(null, null,
                        new SubSelector("tbody tr", new SelectorOptions("Conway", null,
                                new SubSelector("td:has-text('$50.00')", null)))))
        );
    }

    @Test
    void CookieTest() {
        BrowserContext context = actorPage.context();

        actor.attemptsTo(
                Navigate.to("https://google.com"),
                Wait.forLoadState(LoadState.NETWORKIDLE)
        );
        // assert that there are cookies to clear
        assert context.cookies().toArray().length != 0;

        // Clear any cookies not added by us
        actor.attemptsTo(
                Clear.cookies()
        );
        // assert that cookies are successfully cleared
        assert context.cookies().toArray().length == 0;

        // Add some cookies
        List<Cookie> cookiesToAdd = new ArrayList<>();
        cookiesToAdd.add(new Cookie("cookie1", "someValue").setDomain(".google.com").setPath("/")
                .setExpires(1700269944).setHttpOnly(true).setSecure(true).setSameSite(SameSiteAttribute.LAX));
        cookiesToAdd.add(new Cookie("cookie2", "val").setDomain(".google.com").setPath("/")
                .setExpires(1700269944).setHttpOnly(true).setSecure(true).setSameSite(SameSiteAttribute.LAX));

        actor.attemptsTo(
                Add.cookies(cookiesToAdd)
        );
        // assert that cookies are successfully added
        for(int i = 0; i < cookiesToAdd.size(); i++) {
            Cookie c1 = (Cookie) cookiesToAdd.toArray()[i];
            Cookie c2 = (Cookie) context.cookies().toArray()[i];
            assert c1.name.equals(c2.name) && c1.value.equals(c2.value) && c1.domain.equals(c2.domain) && c1.path.equals(c2.path)
                    && c1.expires.equals(c2.expires) && c1.httpOnly.equals(c2.httpOnly) && c1.secure.equals(c2.secure)
                    && c1.sameSite.equals(c2.sameSite);
        }

        // Get the cookies we just added
        @SuppressWarnings("unchecked") // necessary so IntelliJ doesn't complain about unchecked cast (Object to List)
        List<Cookie> getCookies = (List<Cookie>) actor.attemptsTo(
                Get.cookies("https://google.com")
        );
        // assert that cookies are retrieved successfully and have the expected values
        for(int i = 0; i < cookiesToAdd.size(); i++) {
            Cookie c1 = (Cookie) cookiesToAdd.toArray()[i];
            Cookie c2 = (Cookie) getCookies.toArray()[i];
            assert c1.name.equals(c2.name) && c1.value.equals(c2.value) && c1.domain.equals(c2.domain) && c1.path.equals(c2.path)
                    && c1.expires.equals(c2.expires) && c1.httpOnly.equals(c2.httpOnly) && c1.secure.equals(c2.secure)
                    && c1.sameSite.equals(c2.sameSite);
        }
    }

    @Test
    void LocalStorageAndSessionStorageTest() {
        actor.attemptsTo(
                Navigate.to("https://google.com"),
                Wait.forLoadState(LoadState.NETWORKIDLE),

                Set.localStorageItem("localKey", "localValue"),
                Set.sessionStorageItem("sessionKey", "sessionValue")
        );

        // check local storage item
        Object local = actor.attemptsTo(
                Get.localStorageItem("localKey")
        );
        assert (local).equals("localValue");

        // check session storage item
        Object session = actor.attemptsTo(
                Get.sessionStorageItem("sessionKey")
        );
        assert (session).equals("sessionValue");

        // check for values that are not there
        Object localUndefined = actor.attemptsTo(
                Get.localStorageItem("???")
        );
        assert localUndefined == null;

        // check for values that are not there
        Object sessionUndefined = actor.attemptsTo(
                Get.sessionStorageItem("???")
        );
        assert sessionUndefined == null;

        // remove local storage item and verify that it was deleted
        Object localDeleted = actor.attemptsTo(
                Remove.localStorageItem("localKey"),
                Get.localStorageItem("localKey")
        );
        assert localDeleted == null;

        // remove session storage item and verify that it was deleted
        Object sessionDeleted = actor.attemptsTo(
                Remove.sessionStorageItem("sessionKey"),
                Get.sessionStorageItem("sessionKey")
                );
        assert sessionDeleted == null;
    }

    // @Test
    @Disabled("Disabled until Utils.recursiveLocatorLookup is modified!")
    void ElementTest() {
        actor.attemptsTo(
                Navigate.to("https://the-internet.herokuapp.com/tables"),
                Wait.forLoadState(LoadState.NETWORKIDLE)
        );

        assert actor.asks(
                // TODO: remove second parameter (activityResult) from Actor.asks in core package!
                Element.toBe().visible("h3", new SelectorOptions("Data Tables", null, null)), null
        );

        // TODO: rewrite timeout for utils
        assertThrows(RuntimeException.class, () ->
                actor.asks(
                    Element.toBe().visible("h3", new SelectorOptions("this does not exist", 1000.0, null)), null
                )
        );

        assert actor.asks(
                Element.notToBe().visible("h3", new SelectorOptions("this does not exist", 5000.0, null)), null
        );

        assertThrows(RuntimeException.class, () ->
                actor.asks(
                        Element.notToBe().visible("h3", new SelectorOptions("Data Tables", 1000.0, null)), null
                )
        );
    }
    /*

        await actor.attemptsTo(
        Navigate.to("https://the-internet.herokuapp.com/tinymce"),
        Wait.forLoadState("networkidle"),
        Click.on("[aria-label="Bold"]"),
        );

        expect(await actor.asks(
        Element.toBe.enabled("[aria-label="Undo"]"),
        )).toBe(true);

        let enabledRes = false;
        try {
        expect(await actor.asks(
        Element.toBe.enabled("[aria-label="Redo"]", { timeout: 1000 }),
        )).toBe(true);
        } catch (error) {
        enabledRes = true;
        }
        expect(enabledRes).toBeTruthy();

        expect(await actor.asks(
        Element.notToBe.enabled("[aria-label="Redo"]"),
        )).toBe(true);

        let notEnabledRes = false;
        try {
        expect(await actor.asks(
        Element.notToBe.enabled("[aria-label="Undo"]", { timeout: 1000 }),
        )).toBe(true);
        } catch (error) {
        notEnabledRes = true;
        }
        expect(notEnabledRes).toBeTruthy();
        });
        })
    */
}
