import org.junit.Before;
import org.junit.Test;
import org.springframework.web.context.request.WebRequest;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AjaxUtilsTest {

    private WebRequest webRequest;

    @Before
    public void setUp() {
        webRequest = mock(WebRequest.class);
    }

    @Test
    public void testIsAjaxRequest() {
        when(webRequest.getHeader("X-Requested-With")).thenReturn("XMLHttpRequest");
        assertThat(AjaxUtils.isAjaxRequest(webRequest), is(true));

        when(webRequest.getHeader("X-Requested-With")).thenReturn("SomethingElse");
        assertThat(AjaxUtils.isAjaxRequest(webRequest), is(false));
    }

    @Test
    public void testIsAjaxUploadRequest() {
        when(webRequest.getParameter("ajaxUpload")).thenReturn("value");
        assertThat(AjaxUtils.isAjaxUploadRequest(webRequest), is(true));

        when(webRequest.getParameter("ajaxUpload")).thenReturn(null);
        assertThat(AjaxUtils.isAjaxUploadRequest(webRequest), is(false));
    }
}