DESCRIPTION="OpenWrt Configuration Interface"

LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://LICENSE;md5=2b42edef8fa55315f34f2370b4715ca9"

SRC_URI="git://github.com/openwrt/luci.git;protocol=https;branch=luci-0.11 \
	 file://k2m_fix.patch \
	 file://journalctl_instead_of_logread.patch \
	 file://sp_opkg_fix.patch \
	"
SRCREV="668f8ed7a09f3118038e604a234651250c0807e1"

DEPENDS = "lua5.1"

do_compile() {
    make
}

S="${WORKDIR}/git"

do_install() {
    for dist in `find ${S} -name dist` ; do
	tar c -C $dist -O . | tar x -C ${D}
    done
    chown -R root:root ${D}/*
}

PACKAGES =+ "${PN}-app-freifunk-community \
	     ${PN}-app-ahcp \
	     ${PN}-app-ddns \
	     ${PN}-app-diag-core \
	     ${PN}-app-diag-devinfo \
	     ${PN}-app-firewall \
	     ${PN}-app-freifunk-policyrouting \
	     ${PN}-app-hd-idle \
	     ${PN}-app-meshwizard \
	     ${PN}-app-mmc-over-gpio \
	     ${PN}-app-multiwan \
	     ${PN}-app-ntpc \
	     ${PN}-app-olsr-services \
	     ${PN}-app-olsr-viz \
	     ${PN}-app-olsr \
	     ${PN}-app-openvpn \
	     ${PN}-app-p2pblock \
	     ${PN}-app-p910nd \
	     ${PN}-app-pbx-voicemail \
	     ${PN}-app-pbx \
	     ${PN}-app-polipo \
	     ${PN}-app-qos \
	     ${PN}-app-radvd \
	     ${PN}-app-samba \
	     ${PN}-app-siitwizard \
	     ${PN}-app-splash \
	     ${PN}-app-statistics \
	     ${PN}-app-tinyproxy \
	     ${PN}-app-upnp \
	     ${PN}-app-ushare \
	     ${PN}-app-vnstat \
	     ${PN}-app-voice-core \
	     ${PN}-app-voice-diag \
	     ${PN}-app-wol \
	     ${PN}-app-wshaper \
	     ${PN}-app-asterisk \
	     ${PN}-app-commands \
	     ${PN}-app-coovachilli \
	     ${PN}-app-freifunk-diagnostics \
	     ${PN}-app-freifunk-widgets \
	     ${PN}-app-ltqtapi \
	     ${PN}-app-minidlna \
	     ${PN}-app-transmission \
	     ${PN}-app-watchcat \
	     ${PN}-i18n-catalan \
	     ${PN}-i18n-chinese \
	     ${PN}-i18n-english \
	     ${PN}-i18n-french \
	     ${PN}-i18n-german \
	     ${PN}-i18n-greek \
	     ${PN}-i18n-hebrew \
	     ${PN}-i18n-italian \
	     ${PN}-i18n-japanese \
	     ${PN}-i18n-malay \
	     ${PN}-i18n-norwegian \
	     ${PN}-i18n-portuguese-brazilian \
	     ${PN}-i18n-portuguese \
	     ${PN}-i18n-romanian \
	     ${PN}-i18n-russian \
	     ${PN}-i18n-spanish \
	     ${PN}-i18n-vietnamese \
	     ${PN}-i18n-hungarian \
	     ${PN}-i18n-polish \
	     ${PN}-i18n-ukrainian \
	     ${PN}-libs-httpclient \
	     ${PN}-libs-json \
	     ${PN}-libs-nixio \
	     ${PN}-libs-px5g \
	     ${PN}-libs-rpcc \
	     ${PN}-libs-web \
	     ${PN}-libs-core \
	     ${PN}-libs-fastindex \
	     ${PN}-libs-ipkg \
	     ${PN}-libs-lucid-http \
	     ${PN}-libs-lucid-rpc \
	     ${PN}-libs-lucid \
	     ${PN}-libs-sgi-cgi \
	     ${PN}-libs-sgi-luci \
	     ${PN}-libs-sgi-uhttpd \
	     ${PN}-libs-sgi-wsapi \
	     ${PN}-libs-sys \
	     ${PN}-modules-admin-full \
	     ${PN}-modules-admin-mini \
	     ${PN}-modules-freifunk \
	     ${PN}-modules-rpc \
	     ${PN}-modules-admin-core \
	     ${PN}-modules-niu \
	     ${PN}-modules-failsafe \
	     ${PN}-themes-freifunk-bno \
	     ${PN}-themes-freifunk-generic \
	     ${PN}-themes-openwrt \
	     ${PN}-themes-base \
	     ${PN}-themes-bootstrap \
	     "

FILES_${PN}-app-ahcp = " \
${libdir}/lua/luci/controller/ahcp.lua \
${libdir}/lua/luci/model/cbi/ahcp.lua \
${libdir}/lua/luci/view/admin_status/index/ahcp.htm \
${libdir}/lua/luci/view/ahcp_status.htm \
${libdir}/lua/luci/i18n/ahcp.ca.lmo \
${libdir}/lua/luci/i18n/ahcp.cs.lmo \
${libdir}/lua/luci/i18n/ahcp.de.lmo \
${libdir}/lua/luci/i18n/ahcp.el.lmo \
${libdir}/lua/luci/i18n/ahcp.es.lmo \
${libdir}/lua/luci/i18n/ahcp.fr.lmo \
${libdir}/lua/luci/i18n/ahcp.he.lmo \
${libdir}/lua/luci/i18n/ahcp.hu.lmo \
${libdir}/lua/luci/i18n/ahcp.it.lmo \
${libdir}/lua/luci/i18n/ahcp.ja.lmo \
${libdir}/lua/luci/i18n/ahcp.no.lmo \
${libdir}/lua/luci/i18n/ahcp.pl.lmo \
${libdir}/lua/luci/i18n/ahcp.pt-br.lmo \
${libdir}/lua/luci/i18n/ahcp.ro.lmo \
${libdir}/lua/luci/i18n/ahcp.ru.lmo \
${libdir}/lua/luci/i18n/ahcp.tr.lmo \
${libdir}/lua/luci/i18n/ahcp.uk.lmo \
${libdir}/lua/luci/i18n/ahcp.zh-cn.lmo \
${sysconfdir}/uci-defaults/luci-ahcp \
"
FILES_${PN}-app-ddns = " \
${libdir}/lua/luci/controller/ddns.lua \
${libdir}/lua/luci/model/cbi/ddns/ddns.lua \
${libdir}/lua/luci/i18n/ddns.ca.lmo \
${libdir}/lua/luci/i18n/ddns.cs.lmo \
${libdir}/lua/luci/i18n/ddns.de.lmo \
${libdir}/lua/luci/i18n/ddns.el.lmo \
${libdir}/lua/luci/i18n/ddns.es.lmo \
${libdir}/lua/luci/i18n/ddns.fr.lmo \
${libdir}/lua/luci/i18n/ddns.he.lmo \
${libdir}/lua/luci/i18n/ddns.hu.lmo \
${libdir}/lua/luci/i18n/ddns.it.lmo \
${libdir}/lua/luci/i18n/ddns.ja.lmo \
${libdir}/lua/luci/i18n/ddns.no.lmo \
${libdir}/lua/luci/i18n/ddns.pl.lmo \
${libdir}/lua/luci/i18n/ddns.pt-br.lmo \
${libdir}/lua/luci/i18n/ddns.pt.lmo \
${libdir}/lua/luci/i18n/ddns.ro.lmo \
${libdir}/lua/luci/i18n/ddns.ru.lmo \
${libdir}/lua/luci/i18n/ddns.uk.lmo \
${libdir}/lua/luci/i18n/ddns.vi.lmo \
${libdir}/lua/luci/i18n/ddns.zh-cn.lmo \
"
FILES_${PN}-app-diag-core = " \
${libdir}/lua/luci/controller/luci_diag.lua \
${libdir}/lua/luci/view/diag/index.htm \
${libdir}/lua/luci/view/diag/network_config_index.htm \
${libdir}/lua/luci/i18n/diag_core.cs.lmo \
${libdir}/lua/luci/i18n/diag_core.de.lmo \
${libdir}/lua/luci/i18n/diag_core.el.lmo \
${libdir}/lua/luci/i18n/diag_core.es.lmo \
${libdir}/lua/luci/i18n/diag_core.fr.lmo \
${libdir}/lua/luci/i18n/diag_core.hu.lmo \
${libdir}/lua/luci/i18n/diag_core.it.lmo \
${libdir}/lua/luci/i18n/diag_core.ja.lmo \
${libdir}/lua/luci/i18n/diag_core.no.lmo \
${libdir}/lua/luci/i18n/diag_core.pl.lmo \
${libdir}/lua/luci/i18n/diag_core.pt-br.lmo \
${libdir}/lua/luci/i18n/diag_core.pt.lmo \
${libdir}/lua/luci/i18n/diag_core.ro.lmo \
${libdir}/lua/luci/i18n/diag_core.ru.lmo \
${libdir}/lua/luci/i18n/diag_core.uk.lmo \
${libdir}/lua/luci/i18n/diag_core.zh-cn.lmo \
"
FILES_${PN}-app-diag-devinfo = " \
${libdir}/lua/luci/controller/luci_diag/devinfo_common.lua \
${libdir}/lua/luci/controller/luci_diag/luci_diag_devinfo.lua \
${libdir}/lua/luci/controller/luci_diag/netdiscover_common.lua \
${libdir}/lua/luci/controller/luci_diag/smap_common.lua \
${libdir}/lua/luci/model/cbi/luci_diag/mactodevinfo.lua \
${libdir}/lua/luci/model/cbi/luci_diag/netdiscover_devinfo_config.lua \
${libdir}/lua/luci/model/cbi/luci_diag/netdiscover_devinfo_config_mini.lua \
${libdir}/lua/luci/model/cbi/luci_diag/netdiscover_devinfo_mini.lua \
${libdir}/lua/luci/model/cbi/luci_diag/smap_devinfo.lua \
${libdir}/lua/luci/model/cbi/luci_diag/smap_devinfo_config.lua \
${libdir}/lua/luci/model/cbi/luci_diag/smap_devinfo_config_mini.lua \
${libdir}/lua/luci/model/cbi/luci_diag/smap_devinfo_mini.lua \
${libdir}/lua/luci/model/cbi/luci_diag/netdiscover_devinfo.lua \
${libdir}/lua/luci/view/diag/smapsection.htm \
${libdir}/lua/luci/view/diag/smapvalue.htm \
${libdir}/lua/luci/i18n/diag_devinfo.cs.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.de.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.el.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.es.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.fr.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.hu.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.ja.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.no.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.pl.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.pt-br.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.ro.lmo \
${libdir}/lua/luci/i18n/diag_devinfo.ru.lmo \
${sysconfdir}/config/luci_devinfo \
"
FILES_${PN}-app-firewall = " \
${libdir}/lua/luci/controller/firewall.lua \
${libdir}/lua/luci/model/cbi/firewall/custom.lua \
${libdir}/lua/luci/model/cbi/firewall/forward-details.lua \
${libdir}/lua/luci/model/cbi/firewall/forwards.lua \
${libdir}/lua/luci/model/cbi/firewall/zones.lua \
${libdir}/lua/luci/model/cbi/firewall/rule-details.lua \
${libdir}/lua/luci/model/cbi/firewall/rules.lua \
${libdir}/lua/luci/model/cbi/firewall/zone-details.lua \
${libdir}/lua/luci/tools/firewall.lua \
${libdir}/lua/luci/view/firewall/cbi_addforward.htm \
${libdir}/lua/luci/view/firewall/cbi_addrule.htm \
${libdir}/lua/luci/view/firewall/cbi_addsnat.htm \
${libdir}/lua/luci/i18n/firewall.ca.lmo \
${libdir}/lua/luci/i18n/firewall.cs.lmo \
${libdir}/lua/luci/i18n/firewall.de.lmo \
${libdir}/lua/luci/i18n/firewall.el.lmo \
${libdir}/lua/luci/i18n/firewall.es.lmo \
${libdir}/lua/luci/i18n/firewall.fr.lmo \
${libdir}/lua/luci/i18n/firewall.hu.lmo \
${libdir}/lua/luci/i18n/firewall.it.lmo \
${libdir}/lua/luci/i18n/firewall.ja.lmo \
${libdir}/lua/luci/i18n/firewall.no.lmo \
${libdir}/lua/luci/i18n/firewall.pl.lmo \
${libdir}/lua/luci/i18n/firewall.pt-br.lmo \
${libdir}/lua/luci/i18n/firewall.pt.lmo \
${libdir}/lua/luci/i18n/firewall.ro.lmo \
${libdir}/lua/luci/i18n/firewall.ru.lmo \
${libdir}/lua/luci/i18n/firewall.uk.lmo \
${libdir}/lua/luci/i18n/firewall.vi.lmo \
${libdir}/lua/luci/i18n/firewall.zh-cn.lmo \
"
FILES_${PN}-app-freifunk-policyrouting = " \
${libdir}/lua/luci/controller/freifunk/policy-routing.lua \
${libdir}/lua/luci/model/cbi/freifunk/policyrouting.lua \
${libdir}/lua/luci/i18n/freifunk-policyrouting.cs.lmo \
${libdir}/lua/luci/i18n/freifunk-policyrouting.de.lmo \
${libdir}/lua/luci/i18n/freifunk-policyrouting.es.lmo \
${libdir}/lua/luci/i18n/freifunk-policyrouting.pl.lmo \
${libdir}/lua/luci/i18n/freifunk-policyrouting.pt-br.lmo \
${libdir}/lua/luci/i18n/freifunk-policyrouting.ru.lmo \
"
FILES_${PN}-app-hd-idle = " \
${libdir}/lua/luci/controller/hd_idle.lua \
${libdir}/lua/luci/model/cbi/hd_idle.lua \
${libdir}/lua/luci/i18n/hd_idle.ca.lmo \
${libdir}/lua/luci/i18n/hd_idle.cs.lmo \
${libdir}/lua/luci/i18n/hd_idle.de.lmo \
${libdir}/lua/luci/i18n/hd_idle.el.lmo \
${libdir}/lua/luci/i18n/hd_idle.es.lmo \
${libdir}/lua/luci/i18n/hd_idle.fr.lmo \
${libdir}/lua/luci/i18n/hd_idle.he.lmo \
${libdir}/lua/luci/i18n/hd_idle.hu.lmo \
${libdir}/lua/luci/i18n/hd_idle.it.lmo \
${libdir}/lua/luci/i18n/hd_idle.ja.lmo \
${libdir}/lua/luci/i18n/hd_idle.no.lmo \
${libdir}/lua/luci/i18n/hd_idle.pl.lmo \
${libdir}/lua/luci/i18n/hd_idle.pt-br.lmo \
${libdir}/lua/luci/i18n/hd_idle.pt.lmo \
${libdir}/lua/luci/i18n/hd_idle.ro.lmo \
${libdir}/lua/luci/i18n/hd_idle.ru.lmo \
${libdir}/lua/luci/i18n/hd_idle.tr.lmo \
${libdir}/lua/luci/i18n/hd_idle.vi.lmo \
${libdir}/lua/luci/i18n/hd_idle.zh-cn.lmo \
${sysconfdir}/uci-defaults/luci-hd_idle \
"
FILES_${PN}-app-meshwizard = " \
${libdir}/lua/luci/controller/meshwizard.lua \
${libdir}/lua/luci/model/cbi/freifunk/meshwizard.lua \
${libdir}/lua/luci/i18n/meshwizard.de.lmo \
${libdir}/lua/luci/i18n/meshwizard.el.lmo \
${libdir}/lua/luci/i18n/meshwizard.es.lmo \
${libdir}/lua/luci/i18n/meshwizard.fr.lmo \
${libdir}/lua/luci/i18n/meshwizard.it.lmo \
${libdir}/lua/luci/i18n/meshwizard.pl.lmo \
${libdir}/lua/luci/i18n/meshwizard.pt-br.lmo \
${libdir}/lua/luci/i18n/meshwizard.ro.lmo \
${libdir}/lua/luci/i18n/meshwizard.ru.lmo \
${libdir}/lua/luci/i18n/meshwizard.zh-cn.lmo \
${sysconfdir}/uci-defaults/meshwizard \
"
FILES_${PN}-app-mmc-over-gpio = " \
${libdir}/lua/luci/controller/mmc_over_gpio.lua \
${libdir}/lua/luci/model/cbi/mmc_over_gpio.lua \
${libdir}/lua/luci/i18n/mmc_over_gpio.ca.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.cs.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.de.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.el.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.es.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.fr.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.he.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.hu.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.it.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.ja.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.no.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.pl.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.pt-br.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.pt.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.ro.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.ru.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.tr.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.uk.lmo \
${libdir}/lua/luci/i18n/mmc_over_gpio.vi.lmo \
${sysconfdir}/uci-defaults/luci-mmc-over-gpio \
"
FILES_${PN}-app-multiwan = " \
${libdir}/lua/luci/controller/multiwan.lua \
${libdir}/lua/luci/model/cbi/multiwan/multiwan.lua \
${libdir}/lua/luci/model/cbi/multiwan/multiwanmini.lua \
${libdir}/lua/luci/view/admin_status/index/multiwan.htm \
${libdir}/lua/luci/view/multiwan_status.htm \
${libdir}/lua/luci/i18n/multiwan.de.lmo \
${libdir}/lua/luci/i18n/multiwan.el.lmo \
${libdir}/lua/luci/i18n/multiwan.es.lmo \
${libdir}/lua/luci/i18n/multiwan.fr.lmo \
${libdir}/lua/luci/i18n/multiwan.hu.lmo \
${libdir}/lua/luci/i18n/multiwan.it.lmo \
${libdir}/lua/luci/i18n/multiwan.ja.lmo \
${libdir}/lua/luci/i18n/multiwan.no.lmo \
${libdir}/lua/luci/i18n/multiwan.pl.lmo \
${libdir}/lua/luci/i18n/multiwan.pt-br.lmo \
${libdir}/lua/luci/i18n/multiwan.ro.lmo \
${libdir}/lua/luci/i18n/multiwan.ru.lmo \
"
FILES_${PN}-app-ntpc = " \
${libdir}/lua/luci/controller/ntpc.lua \
${libdir}/lua/luci/model/cbi/ntpc/ntpc.lua \
${libdir}/lua/luci/model/cbi/ntpc/ntpcmini.lua \
${libdir}/lua/luci/i18n/ntpc.ca.lmo \
${libdir}/lua/luci/i18n/ntpc.cs.lmo \
${libdir}/lua/luci/i18n/ntpc.de.lmo \
${libdir}/lua/luci/i18n/ntpc.el.lmo \
${libdir}/lua/luci/i18n/ntpc.es.lmo \
${libdir}/lua/luci/i18n/ntpc.fr.lmo \
${libdir}/lua/luci/i18n/ntpc.he.lmo \
${libdir}/lua/luci/i18n/ntpc.hu.lmo \
${libdir}/lua/luci/i18n/ntpc.it.lmo \
${libdir}/lua/luci/i18n/ntpc.ja.lmo \
${libdir}/lua/luci/i18n/ntpc.no.lmo \
${libdir}/lua/luci/i18n/ntpc.pl.lmo \
${libdir}/lua/luci/i18n/ntpc.pt-br.lmo \
${libdir}/lua/luci/i18n/ntpc.pt.lmo \
${libdir}/lua/luci/i18n/ntpc.ro.lmo \
${libdir}/lua/luci/i18n/ntpc.ru.lmo \
${libdir}/lua/luci/i18n/ntpc.tr.lmo \
${libdir}/lua/luci/i18n/ntpc.uk.lmo \
${libdir}/lua/luci/i18n/ntpc.vi.lmo \
${libdir}/lua/luci/i18n/ntpc.zh-cn.lmo \
"
FILES_${PN}-app-olsr-services = " \
${libdir}/lua/luci/controller/services.lua \
${libdir}/lua/luci/view/freifunk-services/services.htm \
"
FILES_${PN}-app-olsr-viz = " \
${libdir}/lua/luci/controller/olsr-viz.lua \
${libdir}/lua/luci/view/olsr-viz/olsr-viz.htm \
/www/cgi-bin/olsr-viz.sh \
/www/cgi-bin/vizdata.sh \
/www/luci-static/resources/olsr-viz.js \
/www/luci-static/resources/olsr-viz/dot_down.gif \
/www/luci-static/resources/olsr-viz/dot_good.gif \
/www/luci-static/resources/olsr-viz/dot_ok.gif \
/www/luci-static/resources/olsr-viz/dot_weak.gif \
/www/luci-static/resources/olsr-viz/node-hna-mini.gif \
/www/luci-static/resources/olsr-viz/node-mini.gif \
"
FILES_${PN}-app-olsr = " \
${libdir}/lua/luci/controller/olsr.lua \
${libdir}/lua/luci/model/cbi/olsr/olsrddisplay.lua \
${libdir}/lua/luci/model/cbi/olsr/olsrdhna.lua \
${libdir}/lua/luci/model/cbi/olsr/olsrd.lua \
${libdir}/lua/luci/model/cbi/olsr/olsrdiface.lua \
${libdir}/lua/luci/model/cbi/olsr/olsrdplugins.lua \
${libdir}/lua/luci/tools/olsr.lua \
${libdir}/lua/luci/view/status-olsr/error_olsr.htm \
${libdir}/lua/luci/view/status-olsr/hna.htm \
${libdir}/lua/luci/view/status-olsr/interfaces.htm \
${libdir}/lua/luci/view/status-olsr/mid.htm \
${libdir}/lua/luci/view/status-olsr/neighbors.htm \
${libdir}/lua/luci/view/status-olsr/overview.htm \
${libdir}/lua/luci/view/status-olsr/routes.htm \
${libdir}/lua/luci/view/status-olsr/smartgw.htm \
${libdir}/lua/luci/view/status-olsr/topology.htm \
${libdir}/lua/luci/i18n/olsr.ca.lmo \
${libdir}/lua/luci/i18n/olsr.de.lmo \
${libdir}/lua/luci/i18n/olsr.el.lmo \
${libdir}/lua/luci/i18n/olsr.es.lmo \
${libdir}/lua/luci/i18n/olsr.fr.lmo \
${libdir}/lua/luci/i18n/olsr.it.lmo \
${libdir}/lua/luci/i18n/olsr.ja.lmo \
${libdir}/lua/luci/i18n/olsr.pl.lmo \
${libdir}/lua/luci/i18n/olsr.pt-br.lmo \
${libdir}/lua/luci/i18n/olsr.pt.lmo \
${libdir}/lua/luci/i18n/olsr.ru.lmo \
${libdir}/lua/luci/i18n/olsr.vi.lmo \
/www/cgi-bin-status.html \
/www/cgi-bin-nodes.html \
${sysconfdir}/config/luci_olsr \
${sysconfdir}/uci-defaults/luci-olsr \
"
FILES_${PN}-app-openvpn = " \
${libdir}/lua/luci/controller/openvpn.lua \
${libdir}/lua/luci/model/cbi/openvpn-advanced.lua \
${libdir}/lua/luci/model/cbi/openvpn-basic.lua \
${libdir}/lua/luci/model/cbi/openvpn.lua \
${libdir}/lua/luci/view/openvpn/cbi-select-input-add.htm \
${libdir}/lua/luci/view/openvpn/pageswitch.htm \
${libdir}/lua/luci/i18n/openvpn.ca.lmo \
${libdir}/lua/luci/i18n/openvpn.cs.lmo \
${libdir}/lua/luci/i18n/openvpn.de.lmo \
${libdir}/lua/luci/i18n/openvpn.el.lmo \
${libdir}/lua/luci/i18n/openvpn.es.lmo \
${libdir}/lua/luci/i18n/openvpn.fr.lmo \
${libdir}/lua/luci/i18n/openvpn.hu.lmo \
${libdir}/lua/luci/i18n/openvpn.it.lmo \
${libdir}/lua/luci/i18n/openvpn.ja.lmo \
${libdir}/lua/luci/i18n/openvpn.pl.lmo \
${libdir}/lua/luci/i18n/openvpn.pt-br.lmo \
${libdir}/lua/luci/i18n/openvpn.pt.lmo \
${libdir}/lua/luci/i18n/openvpn.ru.lmo \
${libdir}/lua/luci/i18n/openvpn.uk.lmo \
${libdir}/lua/luci/i18n/openvpn.vi.lmo \
${libdir}/lua/luci/i18n/openvpn.zh-cn.lmo \
${sysconfdir}/config/openvpn_recipes \
"
FILES_${PN}-app-p2pblock = " \
${libdir}/lua/luci/controller/ff_p2pblock.lua \
${libdir}/lua/luci/model/cbi/luci_fw/p2pblock.lua \
${libdir}/lua/luci/i18n/p2pblock.ca.lmo \
${libdir}/lua/luci/i18n/p2pblock.cs.lmo \
${libdir}/lua/luci/i18n/p2pblock.de.lmo \
${libdir}/lua/luci/i18n/p2pblock.el.lmo \
${libdir}/lua/luci/i18n/p2pblock.es.lmo \
${libdir}/lua/luci/i18n/p2pblock.fr.lmo \
${libdir}/lua/luci/i18n/p2pblock.he.lmo \
${libdir}/lua/luci/i18n/p2pblock.hu.lmo \
${libdir}/lua/luci/i18n/p2pblock.it.lmo \
${libdir}/lua/luci/i18n/p2pblock.no.lmo \
${libdir}/lua/luci/i18n/p2pblock.pl.lmo \
${libdir}/lua/luci/i18n/p2pblock.pt-br.lmo \
${libdir}/lua/luci/i18n/p2pblock.ro.lmo \
${libdir}/lua/luci/i18n/p2pblock.ru.lmo \
${libdir}/lua/luci/i18n/p2pblock.tr.lmo \
${libdir}/lua/luci/i18n/p2pblock.vi.lmo \
${sysconfdir}/uci-defaults/luci-p2pblock \
"
FILES_${PN}-app-p910nd = " \
${libdir}/lua/luci/controller/p910nd.lua \
${libdir}/lua/luci/model/cbi/p910nd.lua \
${libdir}/lua/luci/i18n/p910nd.ca.lmo \
${libdir}/lua/luci/i18n/p910nd.cs.lmo \
${libdir}/lua/luci/i18n/p910nd.de.lmo \
${libdir}/lua/luci/i18n/p910nd.el.lmo \
${libdir}/lua/luci/i18n/p910nd.es.lmo \
${libdir}/lua/luci/i18n/p910nd.fr.lmo \
${libdir}/lua/luci/i18n/p910nd.he.lmo \
${libdir}/lua/luci/i18n/p910nd.hu.lmo \
${libdir}/lua/luci/i18n/p910nd.it.lmo \
${libdir}/lua/luci/i18n/p910nd.ja.lmo \
${libdir}/lua/luci/i18n/p910nd.no.lmo \
${libdir}/lua/luci/i18n/p910nd.pl.lmo \
${libdir}/lua/luci/i18n/p910nd.pt-br.lmo \
${libdir}/lua/luci/i18n/p910nd.pt.lmo \
${libdir}/lua/luci/i18n/p910nd.ru.lmo \
${libdir}/lua/luci/i18n/p910nd.tr.lmo \
${libdir}/lua/luci/i18n/p910nd.vi.lmo \
${libdir}/lua/luci/i18n/p910nd.zh-cn.lmo \
${sysconfdir}/uci-defaults/luci-p910nd \
"
FILES_${PN}-app-pbx-voicemail = " \
${libdir}/lua/luci/controller/pbx-voicemail.lua \
${libdir}/lua/luci/model/cbi/pbx-voicemail.lua \
${libdir}/lua/luci/i18n/pbx-voicemail.de.lmo \
${libdir}/lua/luci/i18n/pbx-voicemail.el.lmo \
${libdir}/lua/luci/i18n/pbx-voicemail.es.lmo \
${libdir}/lua/luci/i18n/pbx-voicemail.pl.lmo \
${libdir}/lua/luci/i18n/pbx-voicemail.pt-br.lmo \
${libdir}/lua/luci/i18n/pbx-voicemail.ru.lmo \
${sysconfdir}/config/pbx-voicemail \
${sysconfdir}/pbx-voicemail/pbx-msmtprc-account-auth.TEMPLATE \
${sysconfdir}/pbx-voicemail/pbx-msmtprc-account-default.TEMPLATE \
${sysconfdir}/pbx-voicemail/pbx-msmtprc-account.TEMPLATE \
${sysconfdir}/pbx-voicemail/pbx-msmtprc-defaults.TEMPLATE \
${sysconfdir}/pbx-voicemail/pbx-send-voicemail \
"
FILES_${PN}-app-pbx = " \
${libdir}/lua/luci/controller/pbx.lua \
${libdir}/lua/luci/model/cbi/pbx-google.lua \
${libdir}/lua/luci/model/cbi/pbx-users.lua \
${libdir}/lua/luci/model/cbi/pbx-voip.lua \
${libdir}/lua/luci/model/cbi/pbx.lua \
${libdir}/lua/luci/model/cbi/pbx-advanced.lua \
${libdir}/lua/luci/model/cbi/pbx-calls.lua \
${libdir}/lua/luci/i18n/pbx.de.lmo \
${libdir}/lua/luci/i18n/pbx.el.lmo \
${libdir}/lua/luci/i18n/pbx.es.lmo \
${libdir}/lua/luci/i18n/pbx.it.lmo \
${libdir}/lua/luci/i18n/pbx.ja.lmo \
${libdir}/lua/luci/i18n/pbx.pl.lmo \
${libdir}/lua/luci/i18n/pbx.pt-br.lmo \
${libdir}/lua/luci/i18n/pbx.ru.lmo \
${sysconfdir}/config/pbx \
${sysconfdir}/config/pbx-advanced \
${sysconfdir}/config/pbx-calls \
${sysconfdir}/config/pbx-google \
${sysconfdir}/config/pbx-users \
${sysconfdir}/config/pbx-voip \
${sysconfdir}/init.d/pbx-asterisk \
${sysconfdir}/pbx-asterisk/asterisk.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_blacklist.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_blacklist_footer.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_blacklist_header.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_default_user.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_disa-check.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_disa-check_footer.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_disa-check_header.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_disa.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_disa_header.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_incoming_context_gtalk_header.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_outgoing_dial_local_user.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_outgoing_gtalk.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_outgoing_pattern_gtalk.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_outgoing_pattern_sip.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_outgoing_sip.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_user_context_footer.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_user_context_header.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_voicemail_disabled.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/gtalk.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/indications.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/jabber.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/jabber_users.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/logger.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/manager.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/modules.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/rtp.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/sip_peer.TEMPLATE \
${sysconfdir}/pbx-asterisk/sip_registration.TEMPLATE \
${sysconfdir}/pbx-asterisk/sip_user.TEMPLATE \
${sysconfdir}/pbx-asterisk/sounds/agent-alreadyon.gsm \
${sysconfdir}/pbx-asterisk/sounds/agent-incorrect.gsm \
${sysconfdir}/pbx-asterisk/sounds/agent-pass.gsm \
${sysconfdir}/pbx-asterisk/sounds/auth-thankyou.gsm \
${sysconfdir}/pbx-asterisk/extensions.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_default.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_incoming_context_gtalk.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_incoming_context_sip.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/extensions_voicemail_enabled.conf.TEMPLATE \
${sysconfdir}/pbx-asterisk/sip.conf.TEMPLATE \
"
FILES_${PN}-app-polipo = " \
${libdir}/lua/luci/controller/polipo.lua \
${libdir}/lua/luci/model/cbi/polipo.lua \
${libdir}/lua/luci/view/polipo_status.htm \
${libdir}/lua/luci/i18n/polipo.ca.lmo \
${libdir}/lua/luci/i18n/polipo.cs.lmo \
${libdir}/lua/luci/i18n/polipo.de.lmo \
${libdir}/lua/luci/i18n/polipo.el.lmo \
${libdir}/lua/luci/i18n/polipo.en.lmo \
${libdir}/lua/luci/i18n/polipo.es.lmo \
${libdir}/lua/luci/i18n/polipo.it.lmo \
${libdir}/lua/luci/i18n/polipo.ja.lmo \
${libdir}/lua/luci/i18n/polipo.pl.lmo \
${libdir}/lua/luci/i18n/polipo.pt-br.lmo \
${libdir}/lua/luci/i18n/polipo.pt.lmo \
${libdir}/lua/luci/i18n/polipo.ru.lmo \
${libdir}/lua/luci/i18n/polipo.vi.lmo \
/usr/sbin/polipo_purge \
${sysconfdir}/uci-defaults/luci-polipo \
"
FILES_${PN}-app-qos = " \
${libdir}/lua/luci/controller/qos.lua \
${libdir}/lua/luci/model/cbi/qos/qos.lua \
${libdir}/lua/luci/model/cbi/qos/qosmini.lua \
${libdir}/lua/luci/i18n/qos.ca.lmo \
${libdir}/lua/luci/i18n/qos.cs.lmo \
${libdir}/lua/luci/i18n/qos.de.lmo \
${libdir}/lua/luci/i18n/qos.el.lmo \
${libdir}/lua/luci/i18n/qos.es.lmo \
${libdir}/lua/luci/i18n/qos.fr.lmo \
${libdir}/lua/luci/i18n/qos.hu.lmo \
${libdir}/lua/luci/i18n/qos.it.lmo \
${libdir}/lua/luci/i18n/qos.ja.lmo \
${libdir}/lua/luci/i18n/qos.no.lmo \
${libdir}/lua/luci/i18n/qos.pl.lmo \
${libdir}/lua/luci/i18n/qos.pt-br.lmo \
${libdir}/lua/luci/i18n/qos.pt.lmo \
${libdir}/lua/luci/i18n/qos.ru.lmo \
${libdir}/lua/luci/i18n/qos.vi.lmo \
${libdir}/lua/luci/i18n/qos.zh-cn.lmo \
"
FILES_${PN}-app-radvd = " \
${libdir}/lua/luci/controller/radvd.lua \
${libdir}/lua/luci/model/cbi/radvd.lua \
${libdir}/lua/luci/model/cbi/radvd/interface.lua \
${libdir}/lua/luci/model/cbi/radvd/dnssl.lua \
${libdir}/lua/luci/model/cbi/radvd/prefix.lua \
${libdir}/lua/luci/model/cbi/radvd/rdnss.lua \
${libdir}/lua/luci/model/cbi/radvd/route.lua \
${libdir}/lua/luci/i18n/radvd.cs.lmo \
${libdir}/lua/luci/i18n/radvd.de.lmo \
${libdir}/lua/luci/i18n/radvd.el.lmo \
${libdir}/lua/luci/i18n/radvd.es.lmo \
${libdir}/lua/luci/i18n/radvd.fr.lmo \
${libdir}/lua/luci/i18n/radvd.it.lmo \
${libdir}/lua/luci/i18n/radvd.ja.lmo \
${libdir}/lua/luci/i18n/radvd.no.lmo \
${libdir}/lua/luci/i18n/radvd.pl.lmo \
${libdir}/lua/luci/i18n/radvd.pt-br.lmo \
${libdir}/lua/luci/i18n/radvd.ro.lmo \
${libdir}/lua/luci/i18n/radvd.ru.lmo \
${libdir}/lua/luci/i18n/radvd.zh-cn.lmo \
${sysconfdir}/uci-defaults/luci-radvd \
"
FILES_${PN}-app-samba = " \
${libdir}/lua/luci/controller/samba.lua \
${libdir}/lua/luci/model/cbi/samba.lua \
${libdir}/lua/luci/i18n/samba.ca.lmo \
${libdir}/lua/luci/i18n/samba.cs.lmo \
${libdir}/lua/luci/i18n/samba.de.lmo \
${libdir}/lua/luci/i18n/samba.el.lmo \
${libdir}/lua/luci/i18n/samba.en.lmo \
${libdir}/lua/luci/i18n/samba.es.lmo \
${libdir}/lua/luci/i18n/samba.fr.lmo \
${libdir}/lua/luci/i18n/samba.hu.lmo \
${libdir}/lua/luci/i18n/samba.it.lmo \
${libdir}/lua/luci/i18n/samba.ja.lmo \
${libdir}/lua/luci/i18n/samba.no.lmo \
${libdir}/lua/luci/i18n/samba.pl.lmo \
${libdir}/lua/luci/i18n/samba.pt-br.lmo \
${libdir}/lua/luci/i18n/samba.pt.lmo \
${libdir}/lua/luci/i18n/samba.ro.lmo \
${libdir}/lua/luci/i18n/samba.ru.lmo \
${libdir}/lua/luci/i18n/samba.uk.lmo \
${libdir}/lua/luci/i18n/samba.vi.lmo \
${libdir}/lua/luci/i18n/samba.zh-cn.lmo \
"
FILES_${PN}-app-siitwizard = " \
${libdir}/lua/luci/controller/siitwizard.lua \
${libdir}/lua/luci/model/cbi/siitwizard.lua \
${sysconfdir}/config/siit \
"
FILES_${PN}-app-splash = " \
${libdir}/lua/luci/controller/splash/splash.lua \
${libdir}/lua/luci/model/cbi/splash/splash.lua \
${libdir}/lua/luci/model/cbi/splash/splashtext.lua \
${libdir}/lua/luci/view/admin_status/splash.htm \
${libdir}/lua/luci/view/splash/blocked.htm \
${libdir}/lua/luci/view/splash/splash.htm \
${libdir}/lua/luci/view/splash_splash/index.htm \
${libdir}/lua/luci/view/splash_splash/splash.htm \
${libdir}/lua/luci/i18n/splash.cs.lmo \
${libdir}/lua/luci/i18n/splash.de.lmo \
${libdir}/lua/luci/i18n/splash.el.lmo \
${libdir}/lua/luci/i18n/splash.es.lmo \
${libdir}/lua/luci/i18n/splash.fr.lmo \
${libdir}/lua/luci/i18n/splash.he.lmo \
${libdir}/lua/luci/i18n/splash.pl.lmo \
${libdir}/lua/luci/i18n/splash.pt-br.lmo \
${libdir}/lua/luci/i18n/splash.ru.lmo \
${libdir}/lua/luci/i18n/splash.zh-cn.lmo \
/usr/sbin/luci-splash \
/www/cgi-bin/splash/splash.sh \
/www/luci/splash/index.html \
${sysconfdir}/config/luci_splash \
${sysconfdir}/init.d/luci_splash \
"
FILES_${PN}-app-statistics = " \
${libdir}/lua/luci/controller/luci_statistics/luci_statistics.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/cpu.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/csv.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/iptables.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/load.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/network.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/ping.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/rrdtool.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/tcpconns.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/unixsock.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/collectd.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/conntrack.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/df.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/disk.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/dns.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/email.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/exec.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/interface.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/irq.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/iwinfo.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/memory.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/netlink.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/nut.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/olsrd.lua \
${libdir}/lua/luci/model/cbi/luci_statistics/processes.lua \
${libdir}/lua/luci/statistics/rrdtool/colors.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/conntrack.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/cpu.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/df.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/disk.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/dns.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/interface.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/iptables.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/irq.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/iwinfo.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/load.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/memory.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/netlink.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/nut.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/olsrd.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/ping.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/processes.lua \
${libdir}/lua/luci/statistics/rrdtool/definitions/tcpconns.lua \
${libdir}/lua/luci/statistics/datatree.lua \
${libdir}/lua/luci/statistics/i18n.lua \
${libdir}/lua/luci/statistics/rrdtool.lua \
${libdir}/lua/luci/view/admin_statistics/index.htm \
${libdir}/lua/luci/view/public_statistics/graph.htm \
${libdir}/lua/luci/i18n/statistics.ca.lmo \
${libdir}/lua/luci/i18n/statistics.cs.lmo \
${libdir}/lua/luci/i18n/statistics.de.lmo \
${libdir}/lua/luci/i18n/statistics.el.lmo \
${libdir}/lua/luci/i18n/statistics.es.lmo \
${libdir}/lua/luci/i18n/statistics.fr.lmo \
${libdir}/lua/luci/i18n/statistics.he.lmo \
${libdir}/lua/luci/i18n/statistics.hu.lmo \
${libdir}/lua/luci/i18n/statistics.it.lmo \
${libdir}/lua/luci/i18n/statistics.ja.lmo \
${libdir}/lua/luci/i18n/statistics.no.lmo \
${libdir}/lua/luci/i18n/statistics.pl.lmo \
${libdir}/lua/luci/i18n/statistics.pt-br.lmo \
${libdir}/lua/luci/i18n/statistics.pt.lmo \
${libdir}/lua/luci/i18n/statistics.ro.lmo \
${libdir}/lua/luci/i18n/statistics.ru.lmo \
${libdir}/lua/luci/i18n/statistics.vi.lmo \
${libdir}/lua/luci/i18n/rrdtool.ca.lmo \
${libdir}/lua/luci/i18n/rrdtool.de.lmo \
${libdir}/lua/luci/i18n/rrdtool.en.lmo \
${libdir}/lua/luci/i18n/rrdtool.es.lmo \
${libdir}/lua/luci/i18n/rrdtool.hu.lmo \
${libdir}/lua/luci/i18n/rrdtool.no.lmo \
${libdir}/lua/luci/i18n/rrdtool.pl.lmo \
${libdir}/lua/luci/i18n/rrdtool.pt-br.lmo \
${libdir}/lua/luci/i18n/rrdtool.pt.lmo \
${libdir}/lua/luci/i18n/rrdtool.ru.lmo \
${bindir}/stat-genconfig \
${sysconfdir}/config/luci_statistics \
${sysconfdir}/init.d/luci_statistics \
${sysconfdir}/uci-defaults/luci-statistics \
"
FILES_${PN}-app-tinyproxy = " \
${libdir}/lua/luci/controller/tinyproxy.lua \
${libdir}/lua/luci/model/cbi/tinyproxy.lua \
${libdir}/lua/luci/view/tinyproxy_status.htm \
${libdir}/lua/luci/i18n/tinyproxy.ca.lmo \
${libdir}/lua/luci/i18n/tinyproxy.de.lmo \
${libdir}/lua/luci/i18n/tinyproxy.el.lmo \
${libdir}/lua/luci/i18n/tinyproxy.es.lmo \
${libdir}/lua/luci/i18n/tinyproxy.fr.lmo \
${libdir}/lua/luci/i18n/tinyproxy.it.lmo \
${libdir}/lua/luci/i18n/tinyproxy.ja.lmo \
${libdir}/lua/luci/i18n/tinyproxy.pl.lmo \
${libdir}/lua/luci/i18n/tinyproxy.pt-br.lmo \
${libdir}/lua/luci/i18n/tinyproxy.pt.lmo \
${libdir}/lua/luci/i18n/tinyproxy.ru.lmo \
${libdir}/lua/luci/i18n/tinyproxy.vi.lmo \
${libdir}/lua/luci/i18n/tinyproxy.zh-cn.lmo \
"
FILES_${PN}-app-upnp = " \
${libdir}/lua/luci/controller/upnp.lua \
${libdir}/lua/luci/model/cbi/upnp/upnp.lua \
${libdir}/lua/luci/view/admin_status/index/upnp.htm \
${libdir}/lua/luci/view/upnp_status.htm \
${libdir}/lua/luci/i18n/upnp.ca.lmo \
${libdir}/lua/luci/i18n/upnp.cs.lmo \
${libdir}/lua/luci/i18n/upnp.de.lmo \
${libdir}/lua/luci/i18n/upnp.es.lmo \
${libdir}/lua/luci/i18n/upnp.fr.lmo \
${libdir}/lua/luci/i18n/upnp.hu.lmo \
${libdir}/lua/luci/i18n/upnp.it.lmo \
${libdir}/lua/luci/i18n/upnp.ja.lmo \
${libdir}/lua/luci/i18n/upnp.no.lmo \
${libdir}/lua/luci/i18n/upnp.pl.lmo \
${libdir}/lua/luci/i18n/upnp.pt-br.lmo \
${libdir}/lua/luci/i18n/upnp.pt.lmo \
${libdir}/lua/luci/i18n/upnp.ro.lmo \
${libdir}/lua/luci/i18n/upnp.ru.lmo \
${libdir}/lua/luci/i18n/upnp.vi.lmo \
${libdir}/lua/luci/i18n/upnp.zh-cn.lmo \
${sysconfdir}/uci-defaults/luci-upnp \
"
FILES_${PN}-app-ushare = " \
${libdir}/lua/luci/controller/ushare.lua \
${libdir}/lua/luci/model/cbi/ushare.lua \
${libdir}/lua/luci/i18n/ushare.ca.lmo \
${libdir}/lua/luci/i18n/ushare.cs.lmo \
${libdir}/lua/luci/i18n/ushare.de.lmo \
${libdir}/lua/luci/i18n/ushare.el.lmo \
${libdir}/lua/luci/i18n/ushare.es.lmo \
${libdir}/lua/luci/i18n/ushare.fr.lmo \
${libdir}/lua/luci/i18n/ushare.he.lmo \
${libdir}/lua/luci/i18n/ushare.hu.lmo \
${libdir}/lua/luci/i18n/ushare.it.lmo \
${libdir}/lua/luci/i18n/ushare.ja.lmo \
${libdir}/lua/luci/i18n/ushare.no.lmo \
${libdir}/lua/luci/i18n/ushare.pl.lmo \
${libdir}/lua/luci/i18n/ushare.pt-br.lmo \
${libdir}/lua/luci/i18n/ushare.pt.lmo \
${libdir}/lua/luci/i18n/ushare.ro.lmo \
${libdir}/lua/luci/i18n/ushare.ru.lmo \
${libdir}/lua/luci/i18n/ushare.tr.lmo \
${libdir}/lua/luci/i18n/ushare.uk.lmo \
${libdir}/lua/luci/i18n/ushare.vi.lmo \
${libdir}/lua/luci/i18n/ushare.zh-cn.lmo \
${sysconfdir}/uci-defaults/luci-ushare \
"
FILES_${PN}-app-vnstat = " \
${libdir}/lua/luci/controller/vnstat.lua \
${libdir}/lua/luci/model/cbi/vnstat.lua \
${libdir}/lua/luci/view/vnstat.htm \
${libdir}/lua/luci/i18n/vnstat.cs.lmo \
${libdir}/lua/luci/i18n/vnstat.de.lmo \
${libdir}/lua/luci/i18n/vnstat.es.lmo \
${libdir}/lua/luci/i18n/vnstat.fr.lmo \
${libdir}/lua/luci/i18n/vnstat.hu.lmo \
${libdir}/lua/luci/i18n/vnstat.it.lmo \
${libdir}/lua/luci/i18n/vnstat.ja.lmo \
${libdir}/lua/luci/i18n/vnstat.no.lmo \
${libdir}/lua/luci/i18n/vnstat.pl.lmo \
${libdir}/lua/luci/i18n/vnstat.pt-br.lmo \
${libdir}/lua/luci/i18n/vnstat.ro.lmo \
${libdir}/lua/luci/i18n/vnstat.ru.lmo \
${sysconfdir}/uci-defaults/luci-vnstat \
"
FILES_${PN}-app-voice-core = " \
${libdir}/lua/luci/controller/luci_voice.lua \
${libdir}/lua/luci/view/luci_voice/index.htm \
${libdir}/lua/luci/view/luci_voice/phone_index.htm \
${libdir}/lua/luci/i18n/voice_core.cs.lmo \
${libdir}/lua/luci/i18n/voice_core.de.lmo \
${libdir}/lua/luci/i18n/voice_core.el.lmo \
${libdir}/lua/luci/i18n/voice_core.es.lmo \
${libdir}/lua/luci/i18n/voice_core.fr.lmo \
${libdir}/lua/luci/i18n/voice_core.he.lmo \
${libdir}/lua/luci/i18n/voice_core.hu.lmo \
${libdir}/lua/luci/i18n/voice_core.it.lmo \
${libdir}/lua/luci/i18n/voice_core.ja.lmo \
${libdir}/lua/luci/i18n/voice_core.no.lmo \
${libdir}/lua/luci/i18n/voice_core.pl.lmo \
${libdir}/lua/luci/i18n/voice_core.pt-br.lmo \
${libdir}/lua/luci/i18n/voice_core.ro.lmo \
${libdir}/lua/luci/i18n/voice_core.ru.lmo \
${libdir}/lua/luci/i18n/voice_core.tr.lmo \
${libdir}/lua/luci/i18n/voice_core.uk.lmo \
"
FILES_${PN}-app-voice-diag = " \
${libdir}/lua/luci/controller/luci_voice/luci_voice_diag.lua \
${libdir}/lua/luci/view/luci_voice/diag_index.htm \
${libdir}/lua/luci/i18n/voice_diag.cs.lmo \
${libdir}/lua/luci/i18n/voice_diag.de.lmo \
${libdir}/lua/luci/i18n/voice_diag.el.lmo \
${libdir}/lua/luci/i18n/voice_diag.en.lmo \
${libdir}/lua/luci/i18n/voice_diag.es.lmo \
${libdir}/lua/luci/i18n/voice_diag.fr.lmo \
${libdir}/lua/luci/i18n/voice_diag.hu.lmo \
${libdir}/lua/luci/i18n/voice_diag.no.lmo \
${libdir}/lua/luci/i18n/voice_diag.pl.lmo \
${libdir}/lua/luci/i18n/voice_diag.pt-br.lmo \
${libdir}/lua/luci/i18n/voice_diag.ro.lmo \
${libdir}/lua/luci/i18n/voice_diag.ru.lmo \
${libdir}/lua/luci/i18n/voice_diag.uk.lmo \
"
FILES_${PN}-app-wol = " \
${libdir}/lua/luci/controller/wol.lua \
${libdir}/lua/luci/model/cbi/wol.lua \
${libdir}/lua/luci/i18n/wol.cs.lmo \
${libdir}/lua/luci/i18n/wol.de.lmo \
${libdir}/lua/luci/i18n/wol.es.lmo \
${libdir}/lua/luci/i18n/wol.fr.lmo \
${libdir}/lua/luci/i18n/wol.hu.lmo \
${libdir}/lua/luci/i18n/wol.it.lmo \
${libdir}/lua/luci/i18n/wol.ja.lmo \
${libdir}/lua/luci/i18n/wol.no.lmo \
${libdir}/lua/luci/i18n/wol.pl.lmo \
${libdir}/lua/luci/i18n/wol.pt-br.lmo \
${libdir}/lua/luci/i18n/wol.ro.lmo \
${libdir}/lua/luci/i18n/wol.ru.lmo \
${libdir}/lua/luci/i18n/wol.uk.lmo \
${libdir}/lua/luci/i18n/wol.zh-cn.lmo \
"
FILES_${PN}-app-wshaper = " \
${libdir}/lua/luci/controller/wshaper.lua \
${libdir}/lua/luci/model/cbi/wshaper.lua \
${libdir}/lua/luci/i18n/wshaper.cs.lmo \
${libdir}/lua/luci/i18n/wshaper.de.lmo \
${libdir}/lua/luci/i18n/wshaper.el.lmo \
${libdir}/lua/luci/i18n/wshaper.es.lmo \
${libdir}/lua/luci/i18n/wshaper.fr.lmo \
${libdir}/lua/luci/i18n/wshaper.hu.lmo \
${libdir}/lua/luci/i18n/wshaper.ja.lmo \
${libdir}/lua/luci/i18n/wshaper.pl.lmo \
${libdir}/lua/luci/i18n/wshaper.pt-br.lmo \
${libdir}/lua/luci/i18n/wshaper.ru.lmo \
${libdir}/lua/luci/i18n/wshaper.uk.lmo \
${sysconfdir}/uci-defaults/wshaper \
"
FILES_${PN}-app-asterisk = " \
${libdir}/lua/luci/asterisk/cc_idd.lua \
${libdir}/lua/luci/asterisk.lua \
${libdir}/lua/luci/controller/asterisk.lua \
${libdir}/lua/luci/model/cbi/asterisk-dialplans.lua \
${libdir}/lua/luci/model/cbi/asterisk-iax-connections.lua \
${libdir}/lua/luci/model/cbi/asterisk-meetme.lua \
${libdir}/lua/luci/model/cbi/asterisk-mod-app.lua \
${libdir}/lua/luci/model/cbi/asterisk-mod-cdr.lua \
${libdir}/lua/luci/model/cbi/asterisk-mod-chan.lua \
${libdir}/lua/luci/model/cbi/asterisk-mod-codec.lua \
${libdir}/lua/luci/model/cbi/asterisk-mod-format.lua \
${libdir}/lua/luci/model/cbi/asterisk-mod-func.lua \
${libdir}/lua/luci/model/cbi/asterisk-mod-pbx.lua \
${libdir}/lua/luci/model/cbi/asterisk-mod-res-feature.lua \
${libdir}/lua/luci/model/cbi/asterisk-mod-res.lua \
${libdir}/lua/luci/model/cbi/asterisk-sip-connections.lua \
${libdir}/lua/luci/model/cbi/asterisk-voice.lua \
${libdir}/lua/luci/model/cbi/asterisk.lua \
${libdir}/lua/luci/model/cbi/asterisk/dialplan_out.lua \
${libdir}/lua/luci/model/cbi/asterisk/dialplans.lua \
${libdir}/lua/luci/model/cbi/asterisk/dialzones.lua \
${libdir}/lua/luci/model/cbi/asterisk/meetme.lua \
${libdir}/lua/luci/model/cbi/asterisk/meetme_settings.lua \
${libdir}/lua/luci/model/cbi/asterisk/phone_sip.lua \
${libdir}/lua/luci/model/cbi/asterisk/phones.lua \
${libdir}/lua/luci/model/cbi/asterisk/trunk_sip.lua \
${libdir}/lua/luci/model/cbi/asterisk/trunks.lua \
${libdir}/lua/luci/model/cbi/asterisk/voicemail.lua \
${libdir}/lua/luci/model/cbi/asterisk/voicemail_settings.lua \
${libdir}/lua/luci/view/asterisk/cbi/cell.htm \
${libdir}/lua/luci/view/asterisk/dialplans.htm \
${libdir}/lua/luci/view/asterisk/dialzones.htm \
${sysconfdir}/config/asterisk \
${sysconfdir}/uci-defaults/luci-asterisk \
"
FILES_${PN}-app-commands = " \
${libdir}/lua/luci/controller/commands.lua \
${libdir}/lua/luci/model/cbi/commands.lua \
${libdir}/lua/luci/view/commands.htm \
${libdir}/lua/luci/i18n/commands.de.lmo \
${libdir}/lua/luci/i18n/commands.es.lmo \
${libdir}/lua/luci/i18n/commands.pl.lmo \
${libdir}/lua/luci/i18n/commands.ro.lmo \
"
FILES_${PN}-app-coovachilli = " \
${libdir}/lua/luci/controller/coovachilli.lua \
${libdir}/lua/luci/model/cbi/coovachilli.lua \
${libdir}/lua/luci/model/cbi/coovachilli_auth.lua \
${libdir}/lua/luci/model/cbi/coovachilli_network.lua \
${libdir}/lua/luci/model/cbi/coovachilli_radius.lua \
${libdir}/lua/luci/i18n/coovachilli.cs.lmo \
${libdir}/lua/luci/i18n/coovachilli.de.lmo \
${libdir}/lua/luci/i18n/coovachilli.es.lmo \
${libdir}/lua/luci/i18n/coovachilli.fr.lmo \
${libdir}/lua/luci/i18n/coovachilli.he.lmo \
${libdir}/lua/luci/i18n/coovachilli.hu.lmo \
${libdir}/lua/luci/i18n/coovachilli.pl.lmo \
${libdir}/lua/luci/i18n/coovachilli.pt-br.lmo \
${libdir}/lua/luci/i18n/coovachilli.ro.lmo \
${libdir}/lua/luci/i18n/coovachilli.ru.lmo \
${libdir}/lua/luci/i18n/coovachilli.tr.lmo \
${libdir}/lua/luci/i18n/coovachilli.uk.lmo \
${libdir}/lua/luci/i18n/coovachilli.zh-cn.lmo \
${sysconfdir}/config/coovachilli \
"
FILES_${PN}-app-freifunk-diagnostics = " \
${libdir}/lua/luci/controller/freifunk/diag.lua \
${libdir}/lua/luci/view/freifunk/diagnostics.htm \
${sysconfdir}/uci-defaults/luci-freifunk-diagnostics \
"
FILES_${PN}-app-freifunk-widgets = " \
${libdir}/lua/luci/controller/freifunk/widgets.lua \
${libdir}/lua/luci/model/cbi/freifunk/widgets/heightwidth.lua \
${libdir}/lua/luci/model/cbi/freifunk/widgets/html.lua \
${libdir}/lua/luci/model/cbi/freifunk/widgets/iframe.lua \
${libdir}/lua/luci/model/cbi/freifunk/widgets/rssfeed.lua \
${libdir}/lua/luci/model/cbi/freifunk/widgets/search.lua \
${libdir}/lua/luci/model/cbi/freifunk/widgets/widget.lua \
${libdir}/lua/luci/model/cbi/freifunk/widgets/widgets_overview.lua \
${libdir}/lua/luci/view/freifunk/widgets/clear/main.htm \
${libdir}/lua/luci/view/freifunk/widgets/html/main.htm \
${libdir}/lua/luci/view/freifunk/widgets/iframe/main.htm \
${libdir}/lua/luci/view/freifunk/widgets/rssfeed/main.htm \
${libdir}/lua/luci/view/freifunk/widgets/search/main.htm \
${sysconfdir}/config/freifunk-widgets \
/lib/upgrade/keep.d/freifunk-widgets \
"
FILES_${PN}-app-ltqtapi = " \
${libdir}/lua/luci/controller/ltqtapi.lua \
${libdir}/lua/luci/model/cbi/luci_ltqtapi/account.lua \
${libdir}/lua/luci/model/cbi/luci_ltqtapi/contact.lua \
${libdir}/lua/luci/view/admin_status/index/telephony.htm \
${libdir}/lua/luci/view/telephony_status.htm \
"
FILES_${PN}-app-minidlna = " \
${libdir}/lua/luci/controller/minidlna.lua \
${libdir}/lua/luci/model/cbi/minidlna.lua \
${libdir}/lua/luci/view/admin_status/index/minidlna.htm \
${libdir}/lua/luci/view/minidlna_status.htm \
${libdir}/lua/luci/i18n/minidlna.de.lmo \
${libdir}/lua/luci/i18n/minidlna.es.lmo \
${libdir}/lua/luci/i18n/minidlna.hu.lmo \
${libdir}/lua/luci/i18n/minidlna.it.lmo \
${libdir}/lua/luci/i18n/minidlna.ja.lmo \
${libdir}/lua/luci/i18n/minidlna.pl.lmo \
${libdir}/lua/luci/i18n/minidlna.pt-br.lmo \
${libdir}/lua/luci/i18n/minidlna.ru.lmo \
${libdir}/lua/luci/i18n/minidlna.zh-cn.lmo \
${sysconfdir}/uci-defaults/luci-minidlna \
"
FILES_${PN}-app-transmission = " \
${libdir}/lua/luci/controller/transmission.lua \
${libdir}/lua/luci/model/cbi/transmission.lua \
${libdir}/lua/luci/i18n/transmission.cs.lmo \
${libdir}/lua/luci/i18n/transmission.de.lmo \
${libdir}/lua/luci/i18n/transmission.es.lmo \
${libdir}/lua/luci/i18n/transmission.hu.lmo \
${libdir}/lua/luci/i18n/transmission.ja.lmo \
${libdir}/lua/luci/i18n/transmission.pl.lmo \
${libdir}/lua/luci/i18n/transmission.pt-br.lmo \
${libdir}/lua/luci/i18n/transmission.ru.lmo \
${libdir}/lua/luci/i18n/transmission.zh-cn.lmo \
${sysconfdir}/uci-defaults/luci-transmission \
"
FILES_${PN}-app-watchcat = " \
${libdir}/lua/luci/controller/watchcat.lua \
${libdir}/lua/luci/model/cbi/watchcat/watchcat.lua \
${libdir}/lua/luci/i18n/watchcat.cs.lmo \
${libdir}/lua/luci/i18n/watchcat.de.lmo \
${libdir}/lua/luci/i18n/watchcat.es.lmo \
${libdir}/lua/luci/i18n/watchcat.hu.lmo \
${libdir}/lua/luci/i18n/watchcat.it.lmo \
${libdir}/lua/luci/i18n/watchcat.ja.lmo \
${libdir}/lua/luci/i18n/watchcat.pl.lmo \
${libdir}/lua/luci/i18n/watchcat.pt-br.lmo \
${libdir}/lua/luci/i18n/watchcat.ru.lmo \
${libdir}/lua/luci/i18n/watchcat.zh-cn.lmo \
${sysconfdir}/uci-defaults/luci-watchcat \
"
FILES_${PN}-i18n-catalan = "${libdir}/lua/luci/i18n/base.ca.lmo ${sysconfdir}/uci-defaults/luci-i18n-catalan"
RPROVIDES_${PN}-i18n-catalan = "${PN}-i18n"
FILES_${PN}-i18n-chinese = "${libdir}/lua/luci/i18n/base.zh-cn.lmo ${sysconfdir}/uci-defaults/luci-i18n-chinese"
RPROVIDES_${PN}-i18n-chinese = "${PN}-i18n"
FILES_${PN}-i18n-english = "${libdir}/lua/luci/i18n/base.en.lmo ${sysconfdir}/uci-defaults/luci-i18n-english"
RPROVIDES_${PN}-i18n-english = "${PN}-i18n"
FILES_${PN}-i18n-french = "${libdir}/lua/luci/i18n/base.fr.lmo ${sysconfdir}/uci-defaults/luci-i18n-french"
RPROVIDES_${PN}-i18n-french = "${PN}-i18n"
FILES_${PN}-i18n-german = "${libdir}/lua/luci/i18n/base.de.lmo ${sysconfdir}/uci-defaults/luci-i18n-german"
RPROVIDES_${PN}-i18n-german = "${PN}-i18n"
FILES_${PN}-i18n-greek = "${libdir}/lua/luci/i18n/base.el.lmo ${sysconfdir}/uci-defaults/luci-i18n-greek"
RPROVIDES_${PN}-i18n-greek = "${PN}-i18n"
FILES_${PN}-i18n-hebrew = "${libdir}/lua/luci/i18n/base.he.lmo ${sysconfdir}/uci-defaults/luci-i18n-hebrew"
RPROVIDES_${PN}-i18n-hebrew = "${PN}-i18n"
FILES_${PN}-i18n-italian = "${libdir}/lua/luci/i18n/base.it.lmo ${sysconfdir}/uci-defaults/luci-i18n-italian"
RPROVIDES_${PN}-i18n-italian = "${PN}-i18n"
FILES_${PN}-i18n-japanese = "${libdir}/lua/luci/i18n/base.ja.lmo ${sysconfdir}/uci-defaults/luci-i18n-japanese"
RPROVIDES_${PN}-i18n-japanese = "${PN}-i18n"
FILES_${PN}-i18n-malay = "${libdir}/lua/luci/i18n/base.ms.lmo ${sysconfdir}/uci-defaults/luci-i18n-malay"
RPROVIDES_${PN}-i18n-malay = "${PN}-i18n"
FILES_${PN}-i18n-norwegian = " ${libdir}/lua/luci/i18n/base.no.lmo ${sysconfdir}/uci-defaults/luci-i18n-norwegian"
RPROVIDES_${PN}-i18n-norwegian = " ${PN}-i18n"
FILES_${PN}-i18n-portuguese-brazilian = " ${libdir}/lua/luci/i18n/base.pt-br.lmo ${sysconfdir}/uci-defaults/luci-i18n-portuguese_brazilian"
RPROVIDES_${PN}-i18n-portuguese-brazilian = " ${PN}-i18n"
FILES_${PN}-i18n-portuguese = " ${libdir}/lua/luci/i18n/base.pt.lmo ${sysconfdir}/uci-defaults/luci-i18n-portuguese "
RPROVIDES_${PN}-i18n-portuguese = " ${PN}-i18n"
FILES_${PN}-i18n-romanian = "${libdir}/lua/luci/i18n/base.ro.lmo ${sysconfdir}/uci-defaults/luci-i18n-romanian"
RPROVIDES_${PN}-i18n-romanian = "${PN}-i18n"
FILES_${PN}-i18n-russian = " ${libdir}/lua/luci/i18n/base.ru.lmo ${sysconfdir}/uci-defaults/luci-i18n-russian "
RPROVIDES_${PN}-i18n-russian = " ${PN}-i18n"
FILES_${PN}-i18n-spanish = " ${libdir}/lua/luci/i18n/base.es.lmo ${sysconfdir}/uci-defaults/luci-i18n-spanish "
RPROVIDES_${PN}-i18n-spanish = " ${PN}-i18n"
FILES_${PN}-i18n-vietnamese = " ${libdir}/lua/luci/i18n/base.vi.lmo ${sysconfdir}/uci-defaults/luci-i18n-vietnamese "
RPROVIDES_${PN}-i18n-vietnamese = " ${PN}-i18n"
FILES_${PN}-i18n-hungarian = "${libdir}/lua/luci/i18n/base.hu.lmo ${sysconfdir}/uci-defaults/luci-i18n-hungarian"
RPROVIDES_${PN}-i18n-hungarian = "${PN}-i18n"
FILES_${PN}-i18n-polish = " ${libdir}/lua/luci/i18n/base.pl.lmo ${sysconfdir}/uci-defaults/luci-i18n-polish "
RPROVIDES_${PN}-i18n-polish = " ${PN}-i18n"
FILES_${PN}-i18n-ukrainian = " ${libdir}/lua/luci/i18n/base.uk.lmo ${sysconfdir}/uci-defaults/luci-i18n-ukrainian "
RPROVIDES_${PN}-i18n-ukrainian = " ${PN}-i18n"
FILES_${PN}-libs-httpclient = "${libdir}/lua/luci/httpclient/receiver.lua /usr/lib/lua/luci/httpclient.lua "
FILES_${PN}-libs-json = " ${libdir}/lua/luci/json.lua "
FILES_${PN}-libs-nixio = " \
${libdir}/lua/nixio.so \
${libdir}/lua/bit.lua \
${libdir}/lua/nixio/fs.lua \
${libdir}/lua/nixio/util.lua \
/lib/upgrade/luci-add-conffiles.sh \
"
FILES_${PN}-libs-px5g = "${libdir}/lua/px5g.so /usr/lib/lua/px5g/util.lua /usr/sbin/px5g-genkeys"
FILES_${PN}-libs-rpcc = " ${libdir}/lua/luci/rpcc/ruci.lua /usr/lib/lua/luci/rpcc.lua "
FILES_${PN}-libs-web = " \
${libdir}/lua/luci/template/parser.so \
${libdir}/lua/luci/cacheloader.lua \
${libdir}/lua/luci/cbi/datatypes.lua \
${libdir}/lua/luci/cbi.lua \
${libdir}/lua/luci/config.lua \
${libdir}/lua/luci/dispatcher.lua \
${libdir}/lua/luci/http/protocol/conditionals.lua \
${libdir}/lua/luci/http/protocol/date.lua \
${libdir}/lua/luci/http/protocol/mime.lua \
${libdir}/lua/luci/http/protocol.lua \
${libdir}/lua/luci/http.lua \
${libdir}/lua/luci/i18n.lua \
${libdir}/lua/luci/sauth.lua \
${libdir}/lua/luci/template.lua \
${libdir}/lua/luci/view/cbi/browser.htm \
${libdir}/lua/luci/view/cbi/button.htm \
${libdir}/lua/luci/view/cbi/cell_valuefooter.htm \
${libdir}/lua/luci/view/cbi/cell_valueheader.htm \
${libdir}/lua/luci/view/cbi/compound.htm \
${libdir}/lua/luci/view/cbi/delegator.htm \
${libdir}/lua/luci/view/cbi/dvalue.htm \
${libdir}/lua/luci/view/cbi/filebrowser.htm \
${libdir}/lua/luci/view/cbi/full_valuefooter.htm \
${libdir}/lua/luci/view/cbi/full_valueheader.htm \
${libdir}/lua/luci/view/cbi/fvalue.htm \
${libdir}/lua/luci/view/cbi/header.htm \
${libdir}/lua/luci/view/cbi/lvalue.htm \
${libdir}/lua/luci/view/cbi/map.htm \
${libdir}/lua/luci/view/cbi/nsection.htm \
${libdir}/lua/luci/view/cbi/nullsection.htm \
${libdir}/lua/luci/view/cbi/tabcontainer.htm \
${libdir}/lua/luci/view/cbi/tabmenu.htm \
${libdir}/lua/luci/view/cbi/tsection.htm \
${libdir}/lua/luci/view/cbi/tvalue.htm \
${libdir}/lua/luci/view/cbi/ucisection.htm \
${libdir}/lua/luci/view/cbi/upload.htm \
${libdir}/lua/luci/view/cbi/valuefooter.htm \
${libdir}/lua/luci/view/cbi/valueheader.htm \
${libdir}/lua/luci/view/cbi/apply_xhr.htm \
${libdir}/lua/luci/view/cbi/dynlist.htm \
${libdir}/lua/luci/view/cbi/firewall_zoneforwards.htm \
${libdir}/lua/luci/view/cbi/firewall_zonelist.htm \
${libdir}/lua/luci/view/cbi/footer.htm \
${libdir}/lua/luci/view/cbi/mvalue.htm \
${libdir}/lua/luci/view/cbi/network_ifacelist.htm \
${libdir}/lua/luci/view/cbi/network_netinfo.htm \
${libdir}/lua/luci/view/cbi/network_netlist.htm \
${libdir}/lua/luci/view/cbi/simpleform.htm \
${libdir}/lua/luci/view/cbi/tblsection.htm \
${libdir}/lua/luci/view/cbi/value.htm \
/www/luci-static/resources/cbi/add.gif \
/www/luci-static/resources/cbi/apply.gif \
/www/luci-static/resources/cbi/arrow.gif \
/www/luci-static/resources/cbi/down.gif \
/www/luci-static/resources/cbi/download.gif \
/www/luci-static/resources/cbi/edit.gif \
/www/luci-static/resources/cbi/fieldadd.gif \
/www/luci-static/resources/cbi/file.gif \
/www/luci-static/resources/cbi/find.gif \
/www/luci-static/resources/cbi/folder.gif \
/www/luci-static/resources/cbi/help.gif \
/www/luci-static/resources/cbi/key.gif \
/www/luci-static/resources/cbi/link.gif \
/www/luci-static/resources/cbi/reload.gif \
/www/luci-static/resources/cbi/remove.gif \
/www/luci-static/resources/cbi/reset.gif \
/www/luci-static/resources/cbi/save.gif \
/www/luci-static/resources/cbi/up.gif \
/www/luci-static/resources/cbi/user.gif \
/www/luci-static/resources/cbi.js \
${sysconfdir}/config/luci \
/lib/uci/upload/ \
"
FILES_${PN}-libs-core = "\
${libdir}/lua/luci/ccache.lua \
${libdir}/lua/luci/debug.lua \
${libdir}/lua/luci/fs.lua \
${libdir}/lua/luci/init.lua \
${libdir}/lua/luci/ip.lua \
${libdir}/lua/luci/ltn12.lua \
${libdir}/lua/luci/model/uci.lua \
${libdir}/lua/luci/model/firewall.lua \
${libdir}/lua/luci/model/network.lua \
${libdir}/lua/luci/store.lua \
${libdir}/lua/luci/util.lua \
${libdir}/lua/luci/version.lua \
${sysconfdir}/config/ucitrack \
/sbin/luci-reload \
"
FILES_${PN}-libs-fastindex = " ${libdir}/lua/luci/fastindex.so "
FILES_${PN}-libs-ipkg = "${libdir}/lua/luci/model/ipkg.lua "
FILES_${PN}-libs-lucid-http = " \
${libdir}/lua/luci/lucid/http.lua \
${libdir}/lua/luci/lucid/http/DirectoryPublisher.lua \
${libdir}/lua/luci/lucid/http/LuciWebPublisher.lua \
${libdir}/lua/luci/lucid/http/Redirector.lua \
${libdir}/lua/luci/lucid/http/handler/catchall.lua \
${libdir}/lua/luci/lucid/http/handler/luci.lua \
${libdir}/lua/luci/lucid/http/handler/file.lua \
${libdir}/lua/luci/lucid/http/server.lua \
"
FILES_${PN}-libs-lucid-rpc = "\
${libdir}/lua/luci/lucid/rpc.lua \
${libdir}/lua/luci/lucid/rpc/ruci.lua \
${libdir}/lua/luci/lucid/rpc/server.lua \
${libdir}/lua/luci/lucid/rpc/system.lua \
"
FILES_${PN}-libs-lucid = "\
${libdir}/lua/luci/lucid/tcpserver.lua \
${libdir}/lua/luci/lucid.lua \
${sysconfdir}/config/lucid \
${sysconfdir}/init.d/lucid \
"
FILES_${PN}-libs-sgi-cgi = "\
${libdir}/lua/luci/sgi/cgi.lua \
/www/cgi-bin/luci \
"
FILES_${PN}-libs-sgi-luci = "\
${libdir}/lua/luci/ttpd/handler/luci.lua \
${libdir}/lucittpd/plugins/luci-webui.lua "
FILES_${PN}-libs-sgi-uhttpd = " ${libdir}/lua/luci/sgi/uhttpd.lua "
FILES_${PN}-libs-sgi-wsapi = "${libdir}/lua/luci/sgi/wsapi.lua "
FILES_${PN}-libs-sys = " \
${libdir}/lua/luci/sys/iptparser.lua \
${libdir}/lua/luci/sys/zoneinfo.lua \
${libdir}/lua/luci/sys/zoneinfo/tzdata.lua \
${libdir}/lua/luci/sys/zoneinfo/tzoffset.lua \
${libdir}/lua/luci/sys.lua \
"
FILES_${PN}-modules-admin-full = " \
${bindir}/luci-bwc \
${libdir}/lua/luci/controller/admin/filebrowser.lua \
${libdir}/lua/luci/controller/admin/index.lua \
${libdir}/lua/luci/controller/admin/uci.lua \
${libdir}/lua/luci/controller/admin/network.lua \
${libdir}/lua/luci/controller/admin/status.lua \
${libdir}/lua/luci/controller/admin/system.lua \
${libdir}/lua/luci/model/cbi/admin_network/proto_ahcp.lua \
${libdir}/lua/luci/model/cbi/admin_network/routes.lua \
${libdir}/lua/luci/model/cbi/admin_network/hosts.lua \
${libdir}/lua/luci/model/cbi/admin_network/iface_add.lua \
${libdir}/lua/luci/model/cbi/admin_network/network.lua \
${libdir}/lua/luci/model/cbi/admin_network/dhcp.lua \
${libdir}/lua/luci/model/cbi/admin_network/ifaces.lua \
${libdir}/lua/luci/model/cbi/admin_network/ipv6.lua \
${libdir}/lua/luci/model/cbi/admin_network/vlan.lua \
${libdir}/lua/luci/model/cbi/admin_network/wifi.lua \
${libdir}/lua/luci/model/cbi/admin_network/wifi_add.lua \
${libdir}/lua/luci/model/cbi/admin_status/processes.lua \
${libdir}/lua/luci/model/cbi/admin_system/backupfiles.lua \
${libdir}/lua/luci/model/cbi/admin_system/buttons.lua \
${libdir}/lua/luci/model/cbi/admin_system/fstab.lua \
${libdir}/lua/luci/model/cbi/admin_system/fstab/swap.lua \
${libdir}/lua/luci/model/cbi/admin_system/fstab/mount.lua \
${libdir}/lua/luci/model/cbi/admin_system/ipkg.lua \
${libdir}/lua/luci/model/cbi/admin_system/leds.lua \
${libdir}/lua/luci/model/cbi/admin_system/admin.lua \
${libdir}/lua/luci/model/cbi/admin_system/startup.lua \
${libdir}/lua/luci/model/cbi/admin_system/crontab.lua \
${libdir}/lua/luci/model/cbi/admin_system/system.lua \
${libdir}/lua/luci/view/admin_network/diagnostics.htm \
${libdir}/lua/luci/view/admin_network/iface_overview.htm \
${libdir}/lua/luci/view/admin_network/iface_status.htm \
${libdir}/lua/luci/view/admin_network/lease_status.htm \
${libdir}/lua/luci/view/admin_network/switch_status.htm \
${libdir}/lua/luci/view/admin_network/wifi_join.htm \
${libdir}/lua/luci/view/admin_network/wifi_status.htm \
${libdir}/lua/luci/view/admin_network/wifi_overview.htm \
${libdir}/lua/luci/view/admin_status/dmesg.htm \
${libdir}/lua/luci/view/admin_status/iptables.htm \
${libdir}/lua/luci/view/admin_status/routes.htm \
${libdir}/lua/luci/view/admin_status/syslog.htm \
${libdir}/lua/luci/view/admin_status/bandwidth.htm \
${libdir}/lua/luci/view/admin_status/connections.htm \
${libdir}/lua/luci/view/admin_status/load.htm \
${libdir}/lua/luci/view/admin_status/wireless.htm \
${libdir}/lua/luci/view/admin_status/index.htm \
${libdir}/lua/luci/view/admin_system/backupfiles.htm \
${libdir}/lua/luci/view/admin_system/clock_status.htm \
${libdir}/lua/luci/view/admin_system/ipkg.htm \
${libdir}/lua/luci/view/admin_system/reboot.htm \
${libdir}/lua/luci/view/admin_system/packages.htm \
${libdir}/lua/luci/view/admin_system/applyreboot.htm \
${libdir}/lua/luci/view/admin_system/flashops.htm \
${libdir}/lua/luci/view/admin_system/upgrade.htm \
${libdir}/lua/luci/view/admin_uci/changelog.htm \
${libdir}/lua/luci/view/admin_uci/apply.htm \
${libdir}/lua/luci/view/admin_uci/changes.htm \
${libdir}/lua/luci/view/admin_uci/revert.htm \
/www/luci-static/resources/bandwidth.svg \
/www/luci-static/resources/connections.svg \
/www/luci-static/resources/load.svg \
/www/luci-static/resources/wifirate.svg \
/www/luci-static/resources/wireless.svg \
"
FILES_${PN}-modules-admin-mini = " \
${libdir}/lua/luci/controller/mini/index.lua \
${libdir}/lua/luci/controller/mini/network.lua \
${libdir}/lua/luci/controller/mini/system.lua \
${libdir}/lua/luci/model/cbi/mini/dhcp.lua \
${libdir}/lua/luci/model/cbi/mini/index.lua \
${libdir}/lua/luci/model/cbi/mini/luci.lua \
${libdir}/lua/luci/model/cbi/mini/network.lua \
${libdir}/lua/luci/model/cbi/mini/passwd.lua \
${libdir}/lua/luci/model/cbi/mini/system.lua \
${libdir}/lua/luci/model/cbi/mini/wifi.lua \
${libdir}/lua/luci/view/mini/applyreboot.htm \
${libdir}/lua/luci/view/mini/reboot.htm \
${libdir}/lua/luci/view/mini/backup.htm \
${libdir}/lua/luci/view/mini/index.htm \
${libdir}/lua/luci/view/mini/upgrade.htm \
"
FILES_${PN}-modules-freifunk = " \
${libdir}/lua/luci/controller/freifunk/remote_update.lua \
${libdir}/lua/luci/controller/freifunk/freifunk.lua \
${libdir}/lua/luci/model/cbi/freifunk/basics.lua \
${libdir}/lua/luci/model/cbi/freifunk/contact.lua \
${libdir}/lua/luci/model/cbi/freifunk/user_index.lua \
${libdir}/lua/luci/model/cbi/freifunk/profile.lua \
${libdir}/lua/luci/model/cbi/freifunk/profile_expert.lua \
${libdir}/lua/luci/view/cbi/osmll_value.htm \
${libdir}/lua/luci/view/freifunk-map/frame.htm \
${libdir}/lua/luci/view/freifunk-map/map.htm \
${libdir}/lua/luci/view/freifunk/profile_error.htm \
${libdir}/lua/luci/view/freifunk/remote_update.htm \
${libdir}/lua/luci/view/freifunk/adminindex.htm \
${libdir}/lua/luci/view/freifunk/contact.htm \
${libdir}/lua/luci/view/freifunk/index.htm \
${libdir}/lua/luci/view/freifunk/public_status.htm \
${libdir}/lua/luci/i18n/freifunk.ca.lmo \
${libdir}/lua/luci/i18n/freifunk.cs.lmo \
${libdir}/lua/luci/i18n/freifunk.de.lmo \
${libdir}/lua/luci/i18n/freifunk.el.lmo \
${libdir}/lua/luci/i18n/freifunk.es.lmo \
${libdir}/lua/luci/i18n/freifunk.he.lmo \
${libdir}/lua/luci/i18n/freifunk.no.lmo \
${libdir}/lua/luci/i18n/freifunk.pl.lmo \
${libdir}/lua/luci/i18n/freifunk.pt-br.lmo \
${libdir}/lua/luci/i18n/freifunk.pt.lmo \
${libdir}/lua/luci/i18n/freifunk.ru.lmo \
${libdir}/lua/luci/i18n/freifunk.vi.lmo \
${libdir}/lua/luci/i18n/freifunk.zh-cn.lmo \
/www/luci-static/flashing.html \
/www/luci-static/resources/OSMLatLon.htm \
/www/luci-static/resources/flashing.gif \
/www/luci-static/resources/freifunk-map/hna.gif \
/www/luci-static/resources/freifunk-map/node.gif \
/www/luci-static/resources/osm.js \
"
FILES_${PN}-modules-rpc = "\
${libdir}/lua/luci/jsonrpc.lua \
${libdir}/lua/luci/controller/rpc.lua \
${libdir}/lua/luci/jsonrpcbind/uci.lua \
"
FILES_${PN}-modules-admin-core = "\
${sysconfdir}/init.d/luci_dhcp_migrate \
${sysconfdir}/init.d/luci_fixtime \
${libdir}/lua/luci/controller/admin/servicectl.lua \
${libdir}/lua/luci/tools/status.lua \
${libdir}/lua/luci/tools/webadmin.lua \
${libdir}/lua/luci/view/error404.htm \
${libdir}/lua/luci/view/error500.htm \
${libdir}/lua/luci/view/footer.htm \
${libdir}/lua/luci/view/header.htm \
${libdir}/lua/luci/view/indexer.htm \
${libdir}/lua/luci/view/sysauth.htm \
/www/index.html \
"
FILES_${PN}-modules-niu = "\
${sysconfdir}/uci-defaults/luci-niu-firstrun \
${libdir}/lua/luci/niulib.lua \
${libdir}/lua/luci/controller/niu/* \
${libdir}/lua/luci/model/cbi/niu/* \
${libdir}/lua/luci/view/niu/* \
/www/luci-static/resources/niu.css \
/www/luci-static/resources/icons32/folder.png \
/www/luci-static/resources/icons32/network-workgroup.png \
/www/luci-static/resources/icons32/preferences-system.png \
/www/luci-static/resources/icons32/network-wireless.png \
/www/luci-static/resources/icons32/preferences-system-network.png \
"
FILES_${PN}-modules-failsafe = " \
${libdir}/lua/luci/controller/failsafe/failsafe.lua \
${libdir}/lua/luci/view/failsafe/* \
"
FILES_${PN}-themes-freifunk-bno = "\
${sysconfdir}/uci-defaults\luci-theme-freifunk-bno \
${libdir}/lua/luci/view/themes/freifunk-bno/* \
/www/luci-static/freifunk-bno/* \
"
RPROVIDES_${PN}-themes-freifunk-bno = "${PN}-theme"
FILES_${PN}-themes-freifunk-generic = " \
${libdir}/lua/luci/view/themes/freifunk-generic/footer.htm \
${libdir}/lua/luci/view/themes/freifunk-generic/header.htm \
/www/luci-static/freifunk-generic/bg.jpg \
/www/luci-static/freifunk-generic/header.jpg \
/www/luci-static/freifunk-generic/ie7.css \
/www/luci-static/freifunk-generic/logo.jpg \
/www/luci-static/freifunk-generic/mobile.css \
/www/luci-static/freifunk-generic/smallscreen.css \
/www/luci-static/freifunk-generic/cascade.css \
${sysconfdir}/uci-defaults/luci-theme-freifunk-generic \
"
RPROVIDES_${PN}-themes-freifunk-generic = "${PN}-theme"
FILES_${PN}-themes-openwrt = " \
${sysconfdir}/uci-defaults/luci-theme-openwrt \
${libdir}/lua/luci/view/themes/openwrt.org/footer.htm \
${libdir}/lua/luci/view/themes/openwrt.org/header.htm \
/www/luci-static/openwrt.org/* \
"
RPROVIDES_${PN}-themes-openwrt = "${PN}-theme"
FILES_${PN}-themes-base = "\
/www/luci-static/resources/xhr.js \
/www/luci-static/resources/icons/* \
"
FILES_${PN}-themes-bootstrap += "\
${libdir}/lua/luci/view/themes/bootstrap/footer.htm \
${libdir}/lua/luci/view/themes/bootstrap/header.htm \
/www/luci-static/bootstrap/cascade.css \
/www/luci-static/bootstrap/favicon.ico \
/www/luci-static/bootstrap/html5.js \
${sysconfdir}/uci-defaults/ \
${sysconfdir}/uci-defaults/luci-theme-bootstrap \
"
RPROVIDES_${PN}-themes-bootstrap += "${PN}-theme"
FILES_${PN}-dbg += " \
${libdir}/lua/.debug \
${libdir}/lua/luci/.debug \
${libdir}/lua/luci/template/.debug \
${libdir}/lua/luci/template/.debug/parser.so \
${libdir}/lua/luci/.debug/fastindex.so \
${libdir}/lua/.debug/nixio.so \
${libdir}/lua/.debug/px5g.so \
"

RDEPENDS_${PN}-theme += "${PN}-themes-base"

RDEPNDS_${PN}-libs-sgi-cgi += "${PN}-libs-web ${PN}-libs-core \
			       ${PN}-libs-nixio ${PN}-libs-sys \
			       ${PN}-libs-core ${PN}-theme \
			       ${PN}-libs-admin-full ${PN}-i18n \
			       ${PN}-libs-ipkg \
			       libubox libubus-lua libuci-lua \
			       bridge-utils uci \
			       "
RDEPNDS_${PN}-modules-admin-core += "${PN}-libs-sgi-cgi"

