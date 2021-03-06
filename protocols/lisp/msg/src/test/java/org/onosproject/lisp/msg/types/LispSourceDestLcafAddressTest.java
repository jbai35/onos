/*
 * Copyright 2016-present Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.lisp.msg.types;

import com.google.common.testing.EqualsTester;
import org.junit.Before;
import org.junit.Test;
import org.onlab.packet.IpAddress;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Unit tests for LispSourceDestLcafAddress class.
 */
public class LispSourceDestLcafAddressTest {

    private LispSourceDestLcafAddress address1;
    private LispSourceDestLcafAddress sameAsAddress1;
    private LispSourceDestLcafAddress address2;

    @Before
    public void setup() {

        LispIpv4Address srcAddress1 = new LispIpv4Address(IpAddress.valueOf("192.168.1.1"));
        LispIpv4Address dstAddress1 = new LispIpv4Address(IpAddress.valueOf("192.168.1.2"));

        address1 = new LispSourceDestLcafAddress((short) 1, (byte) 0x01,
                                        (byte) 0x01, srcAddress1, dstAddress1);

        sameAsAddress1 = new LispSourceDestLcafAddress((short) 1, (byte) 0x01,
                                        (byte) 0x01, srcAddress1, dstAddress1);

        LispIpv4Address srcAddress2 = new LispIpv4Address(IpAddress.valueOf("192.168.2.1"));
        LispIpv4Address dstAddress2 = new LispIpv4Address(IpAddress.valueOf("192.168.2.2"));

        address2 = new LispSourceDestLcafAddress((short) 2, (byte) 0x02,
                                        (byte) 0x02, srcAddress2, dstAddress2);
    }

    @Test
    public void testEquality() {
        new EqualsTester()
                .addEqualityGroup(address1, sameAsAddress1)
                .addEqualityGroup(address2).testEquals();
    }

    @Test
    public void testConstruction() {
        LispSourceDestLcafAddress sourceDestLcafAddress = address1;

        LispIpv4Address srcAddress = new LispIpv4Address(IpAddress.valueOf("192.168.1.1"));
        LispIpv4Address dstAddress = new LispIpv4Address(IpAddress.valueOf("192.168.1.2"));

        assertThat(sourceDestLcafAddress.getReserved(), is((short) 1));
        assertThat(sourceDestLcafAddress.getSrcMaskLength(), is((byte) 0x01));
        assertThat(sourceDestLcafAddress.getDstMaskLength(), is((byte) 0x01));
        assertThat(sourceDestLcafAddress.getSrcPrefix(), is(srcAddress));
        assertThat(sourceDestLcafAddress.getDstPrefix(), is(dstAddress));
    }
}
