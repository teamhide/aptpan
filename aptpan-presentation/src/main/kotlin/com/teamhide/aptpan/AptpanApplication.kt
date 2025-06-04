package com.teamhide.aptpan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
	scanBasePackages = [
		"com.teamhide.aptpan.application",
		"com.teamhide.aptpan.domain",
		"com.teamhide.aptpan.infrastructure",
	]
)
class AptpanApplication

fun main(args: Array<String>) {
	runApplication<AptpanApplication>(*args)
}
