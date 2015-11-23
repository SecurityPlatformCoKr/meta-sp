# iot-black

iot-black is os distribution for open-source hardware like raspberrypi 2, intel edison, and etc.

iot-black requires TPM is attached to successfully operate. You will be able to buy TPM module from our website shortly.

## Q&A

You can ask questions about these codes at https://groups.google.com/forum/#!forum/devicesecurityplatform

## build howto's

### to build raspberrypi 2, do:

    wget https://raw.githubusercontent.com/SecurityPlatformCoKr/meta-sp/master/meta-sp-raspberrypi/utils/setup/setup.sh
    sh setup.sh
    source poky/oe-init-build-env
    bitbake sp-rpi-image

to complete image build, you should go to poky/iot-black/keys and do:

    cd poky/iot-black/keys
    sh README
