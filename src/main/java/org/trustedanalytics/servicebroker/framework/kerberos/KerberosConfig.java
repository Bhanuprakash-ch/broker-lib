/**
 * Copyright (c) 2015 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.trustedanalytics.servicebroker.framework.kerberos;

import java.io.IOException;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.trustedanalytics.hadoop.config.client.*;
import org.trustedanalytics.servicebroker.framework.Profiles;

import javax.validation.constraints.NotNull;

@Configuration
public class KerberosConfig {

  @Value("${krb.realm:}")
  @NotNull
  private String realm;

  @Value("${krb.kdc:}")
  @NotNull
  private String kdc;

  @Bean
  @Profile(Profiles.CLOUD)
  public KerberosProperties getKerberosProperties() throws IOException {
    if(!Strings.isNullOrEmpty(realm) && !Strings.isNullOrEmpty(kdc)) {
      return new KerberosProperties(kdc, realm, true);
    }
    else {
      return new KerberosProperties(kdc, realm, false);
    }
  }

}
