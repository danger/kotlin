import com.danger.dangerkt.lib.DSL
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test
import resources.dangerDSLJSON

class DSLTest {
    @Test
    fun simpleTest() {
        val gson = Gson()
        gson.fromJson(dangerDSLJSON, DSL::class.java)
        Assert.assertEquals(2, 1 + 1)
    }
}