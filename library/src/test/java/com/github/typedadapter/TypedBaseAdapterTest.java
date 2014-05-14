package com.github.typedadapter;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.github.typedadapter.TypedBaseAdapterTest.TestView;

import android.content.Context;
import android.view.View;

@RunWith(RobolectricTestRunner.class)
public class TypedBaseAdapterTest {

    @Before
    public void setUp() throws Exception {
        new File("tmp").mkdir();
        System.setProperty("dexmaker.dexcache", "tmp");
    }

    @Test
    public void testGetView_baseAdapter() {
        // given
        TypedBaseAdapter<String, TestView> adapter = new TypedBaseAdapter<String, TestView>(new String[] { "hello" }) {
            @Override
            public TestView createView() {
                return null;
            }
        };

        // when

        // then
        assertEquals(1,adapter.getCount());
        assertEquals("hello",adapter.getItem(0));
        assertEquals("hello".hashCode(),adapter.getItemId(0));
    }
    
    @Test
    public void testGetView_refill() {
        // given
        TypedBaseAdapter<String, TestView> adapter = new TypedBaseAdapter<String, TestView>(new String[] { "hello" }) {
            @Override
            public TestView createView() {
                return null;
            }
        };

        // when
        adapter.setObjects(new String[] {"foo"});

        // then
        assertEquals(1,adapter.getCount());
        assertEquals("foo",adapter.getItem(0));
    }

    @Test
    public void testGetView_calls_update() {
        // given
        final TestView mockView = Mockito.mock(TestView.class);

        TypedBaseAdapter<String, TestView> adapter = new TypedBaseAdapter<String, TestView>(new String[] { "hello" }) {
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

        TypedBaseAdapterExtension adapter = new TypedBaseAdapterExtension(new String[] { "hello" }, mockView);

        // when
        adapter.getView(0, null, null);

        // then
        assertTrue(adapter.isCreateViewCalled);
        Mockito.verify(mockView).update(Mockito.anyString());
    }

    private final class TypedBaseAdapterExtension extends TypedBaseAdapter<String, TestView> {
        private final TestView mockView;
        private boolean isCreateViewCalled;

        private TypedBaseAdapterExtension(String[] objects, TestView mockView) {
            super(objects);
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
