import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class PatternTest {

    @Nested
    @DisplayName("Mock Object Test")
    class MockObjectTest {
        private Database db;
        @BeforeEach
        void setupMockDb(){
            db = new MockDatabase();
            db.setQueryAndResult("select order_no from Order where cust_no is 123",
                    Arrays.asList("Order2", "Order3"));
        }

        @Test
        void getNumberOfOrderTest() {
            db.executeQuery("select order_no from Order where cust_no is 123");
            List<String> results = db.getResultSets();
            assertThat(results.size(), is(2));
            db.executeQuery("select custom_no from Customers where order_no is 2");
            results = db.getResultSets();
            assertThat(results.size(), is(0));
        }
    }

    @Nested
    @DisplayName("Crash Test Dummy Test")
    class CrashDummyTest {

        void saveAs(File f) throws IOException {
            f.createNewFile();
        }

        @Test
        void fileSystemErrorTest() {
            File f = new OutOfMemoryFile("foo");
            try {
                saveAs(f);
                Assertions.fail();
            } catch (IOException e) {
            }
        }

        @Test
        void fileSystemErrorTestWithAnonymousInnerClass() throws IOException{
            File f = new OutOfMemoryFile("foo"){
                @Override
                public boolean createNewFile() throws IOException {
                    throw new IOException();
                }
            };

            try {
                saveAs(f);
                Assertions.fail();
            } catch (IOException e) {
            }
        }
    }

}
