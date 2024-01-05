/*
 * Copyright (c) LendUX, LLC. All rights reserved.
 *
 * No part of this software code or its documentation,  may be copied,
 * modified, merged, published,  distributed, sub-licensed, or sold
 * without the express written permission of LendUX, LLC.
 *
 * Unauthorized use, reproduction, publication or distribution of this software,
 * in whole or in part, is strictly prohibited by law.
 *
 * This software and its accompanying documentation are confidential and proprietary
 * information of LendUX, LLC.
 *
 * This software is proprietary to LendUX, LLC, and is provided "as is" without warranty
 * of any kind, express or implied. In no event shall LendUX, LLC be liable for any direct,
 * indirect, incidental, special, exemplary, or consequential damages (including, but not
 * limited to, procurement of substitute goods or services; loss of use, data, or profits;
 * or business interruption) arising in any way out of the use of this software, even if
 * advised of the possibility of such damage.
 */

package com.okta.Okta.Demo.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private static final String PROP_RESOURCE_NOT_FOUND = "Resource Not Found";
    private final String english;
    private final String tech;

    public ResourceNotFoundException(String message){
        super(message);

        tech = message;
        english = null;
    }
}