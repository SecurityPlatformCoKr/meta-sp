# iot-black

iot-black is os distribution for open-source hardware like raspberrypi 2, intel edison, and etc.

iot-black requires TPM is attached to successfully operate. You will be able to buy TPM module from our website shortly.

## build howto's

### to build raspberrypi 2, do:

    wget https://raw.githubusercontent.com/SecurityPlatformCoKr/meta-sp/master/meta-sp-raspberrypi/utils/setup/setup.sh
    sh setup.sh
    source poky/oe-init-build-env
    bitbake sp-rpi-image
to complete image build, you should go poky/iot-black/keys and do as README says:

    cd poky/iot-black/keys
    openssl genrsa -F4 -out dev.key 2048
    openssl req -batch -new -x509 -key dev.key -out dev.crt
