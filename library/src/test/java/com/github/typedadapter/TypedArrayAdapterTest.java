package com.github.typedadapter;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.github.typedadapter.TypedArrayAdapterTest.TestView;

import android.content.Context;
import android.view.View;

@RunWith(RobolectricTestRunner.class)
public class TypedArrayAdapterTest {

    @Before
    public void setUp() throws Exception {
        new File("tmp").mkdir();
        System.setProperty("dexmaker.dexcache", "tmp");
    }

    @Test
    public void testGetView_calls_update() {
        // given
        final TestView mockView = Mockito.mock(TestView.class);

        TypedArrayAdapter<String, TestView> adapter = new TypedArrayAdapter<String, TestView>(Robolectric.application, new String[] { "hello" }) {
            @Override
            public TestView createView() {
                return mockView;
            }
        };

        // when
        adapter.getView(0, mockView, null);

        // then
        Mockito.verify(mockView).update(Mockito.anyString());
    }
    
    @Test
    public void testGetView_calls_createView_and_update() {
        // given
        final TestView mockView = Mockito.mock(TestView.class);

        TypedArrayAdapterExtension adapter = new TypedArrayAdapterExtension(Robolectric.application, new String[] { "hello" }, mockView);

        // when
        adapter.getView(0, null, null);

        // then
        assertTrue(adapter.isCreateViewCalled);
        Mockito.verify(mockView).update(Mockito.anyString());
    }

    private final class TypedArrayAdapterExtension extends TypedArrayAdapter<String, TestView> {
        private final TestView mockView;
        private boolean isCreateViewCalled;

        private TypedArrayAdapterExtension(Context context, String[] objects, TestView mockView) {
            super(context, objects);
            this.mockView = mockView;
        }

        @Override
        public TestView createView() {
            isCreateViewCalled = true;
            return mockView;
        }

        public boolean isCreateViewCalled() {
            return isCreateViewCalled;
        }
    }

    class TestView extends View implements TypedCellView<String> {
        public TestView(Context context) {
            super(context);
        }

        @Override
        public void update(String t) {
        }
    }

}
