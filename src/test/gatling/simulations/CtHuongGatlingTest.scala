import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the CtHuong entity.
 */
class CtHuongGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://127.0.0.1:8080"""

    val httpConf = http
        .baseURL(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the CtHuong entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJSON
        .check(header.get("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(1)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all ctHuongs")
            .get("/api/ct-huongs")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new ctHuong")
            .post("/api/ct-huongs")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "keysl":"SAMPLE_TEXT", "keyYp":"SAMPLE_TEXT", "maDv":"SAMPLE_TEXT", "soBhxh":"SAMPLE_TEXT", "maNv":"SAMPLE_TEXT", "hoTen":"SAMPLE_TEXT", "mucLuong":null, "ngayDuyet":"2020-01-01T00:00:00.000Z", "nguoiDuyet":"SAMPLE_TEXT", "loaiDt":"SAMPLE_TEXT", "maNt":"SAMPLE_TEXT", "mlTt":null, "maCd":"SAMPLE_TEXT", "maNh":"SAMPLE_TEXT", "tuNgay":"2020-01-01T00:00:00.000Z", "denNgay":"2020-01-01T00:00:00.000Z", "tuNgayH":"2020-01-01T00:00:00.000Z", "denNgayH":"2020-01-01T00:00:00.000Z", "soNgay":"0", "soNgayH":"0", "soNgayLk":"0", "soTien":null, "soTienH":null, "soNamBh":"0", "soThangBh":"0", "soNgayLkdv":"0", "ghiChu":"SAMPLE_TEXT", "dk1":"0", "dk2":"0", "dk5":"0", "dk6":"0", "tuoiCon":"0", "sttCon":"0", "gioiTinh":"0", "maQt":"SAMPLE_TEXT", "dkBenhDai":null, "dkPhauthuat":"0", "dkNghionha":"0", "dkSuygiamld":"0", "dkXacsyt":"0", "dk3Ca":"0", "ngay1":"2020-01-01T00:00:00.000Z", "tenCon":"SAMPLE_TEXT", "stt":"0", "maQl":"SAMPLE_TEXT", "maTinh":"SAMPLE_TEXT", "loaidc":"0", "lydodc":"SAMPLE_TEXT", "ghichudc":"SAMPLE_TEXT", "loaiBenh":"SAMPLE_TEXT", "tuyenBv":"SAMPLE_TEXT", "lbqhs":null, "lbqml":null, "troCap":null, "troCapBh":null, "dk3":"0", "dk4":"0", "tyleh":null, "ngayNuoi":"2020-01-01T00:00:00.000Z", "soNgayCd":"0", "ngayNghiList":"SAMPLE_TEXT", "vssid":"SAMPLE_TEXT", "tuNgayBs":"2020-01-01T00:00:00.000Z", "denNgayBs":"2020-01-01T00:00:00.000Z", "chk1":"0", "chk2":"0", "chk3":"0"}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_ctHuong_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created ctHuong")
                .get("${new_ctHuong_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created ctHuong")
            .delete("${new_ctHuong_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(100) over (1 minutes))
    ).protocols(httpConf)
}
