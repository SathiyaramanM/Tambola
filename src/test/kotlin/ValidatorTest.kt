import ai.sahaj.Validator
import ai.sahaj.Claim
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ValidatorTest {
    @Test
    fun `validator should accept a valid top-row claim`() {
        val ticket = arrayOf(
            arrayOf(4, 16 ,null, null, 48, null, 63, 76, null),
            arrayOf(7, null, 23, 38, null, 52, null, null, 80),
            arrayOf(9, null, 25, null, null, 56, 64, null, 83)
        )
        val announcedValues = listOf(90, 4, 46, 63, 89, 16, 76, 48)
        val validator = Validator()
        val result = validator.validate(ticket, announcedValues, Claim.TOP_ROW)
        assertEquals(true, result)
    }

    @Test
    fun `validator should reject a late top-row claim`() {
        val ticket = arrayOf(
            arrayOf(4, 16 ,null, null, 48, null, 63, 76, null),
            arrayOf(7, null, 23, 38, null, 52, null, null, 80),
            arrayOf(9, null, 25, null, null, 56, 64, null, 83)
        )
        val announcedValues = listOf(90, 4, 46, 63, 89, 16, 76, 48, 12)
        val validator = Validator()
        val result = validator.validate(ticket, announcedValues, Claim.TOP_ROW)
        assertEquals(false, result)
    }
}