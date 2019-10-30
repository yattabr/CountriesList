package br.com.wobbu.restcountries.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

object MockServerDispatcher {
    /**
     * Return ok response from mock server
     */
    class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            when (request.path) {
                "/all" -> {
                    return MockResponse().setResponseCode(200).setBody(
                        TestUtils().getFileString(
                            "mock/countries.json"
                        )
                    )
                }
            }
            return MockResponse().setResponseCode(404)
        }
    }

    /**
     * Return error response from mock server
     */
    class ErrorDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {

            return MockResponse().setResponseCode(400)

        }
    }
}