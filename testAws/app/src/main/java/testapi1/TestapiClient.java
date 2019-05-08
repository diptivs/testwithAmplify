/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package testapi1;

@com.amazonaws.mobileconnectors.apigateway.annotation.Service(endpoint = "https://il71phyykd.execute-api.us-west-2.amazonaws.com/dev")
public interface TestapiClient {


    /**
     * A generic invoker to invoke any API Gateway endpoint.
     * @param request
     * @return ApiResponse
     */
    com.amazonaws.mobileconnectors.apigateway.ApiResponse execute(com.amazonaws.mobileconnectors.apigateway.ApiRequest request);

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/config", method = "POST")
    void apiConfigPost();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/config", method = "OPTIONS")
    void apiConfigOptions();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/config/{id}", method = "GET")
    void apiConfigIdGet();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/config/{id}", method = "DELETE")
    void apiConfigIdDelete();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/config/{id}", method = "OPTIONS")
    void apiConfigIdOptions();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/data", method = "POST")
    void apiDataPost();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/data", method = "OPTIONS")
    void apiDataOptions();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/data/{id}", method = "GET")
    void apiDataIdGet();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/data/{id}", method = "DELETE")
    void apiDataIdDelete();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/data/{id}", method = "OPTIONS")
    void apiDataIdOptions();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/history", method = "POST")
    void apiHistoryPost();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/history", method = "OPTIONS")
    void apiHistoryOptions();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/history/{id}", method = "GET")
    void apiHistoryIdGet();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/history/{id}", method = "DELETE")
    void apiHistoryIdDelete();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/history/{id}", method = "OPTIONS")
    void apiHistoryIdOptions();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/owner", method = "GET")
    void apiOwnerGet();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/owner", method = "PUT")
    void apiOwnerPut();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/owner", method = "POST")
    void apiOwnerPost();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/owner", method = "DELETE")
    void apiOwnerDelete();

    /**
     *
     *
     * @return void
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/api/owner", method = "OPTIONS")
    void apiOwnerOptions();

}

