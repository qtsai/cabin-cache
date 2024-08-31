package nl.tsai.cabincache

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter
import java.util.logging.Logger

@SpringBootApplication
class ExampleApplication

fun main(args: Array<String>) {
    runApplication<ExampleApplication>(*args)
}

@Configuration
class RouterConfiguration {

    @Bean
    fun mainRouter() = coRouter {
        accept(MediaType.APPLICATION_OCTET_STREAM).nest {
            GET("ping") {
                val flow = flow {
                    for (i in 1..5) {
                        delay(500)
                        logger.info("ping-$i")
                        emit("pong-$i\n")
                    }
                }
                ok().bodyAndAwait(flow)
            }
        }
    }

    companion object {
        val logger: Logger = Logger.getLogger(RouterConfiguration::class.java.name)
    }
}