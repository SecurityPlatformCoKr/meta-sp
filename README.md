# iot-black

iot-black is os distribution for open-source hardware like raspberrypi 2, intel edison, and etc.

iot-black requires TPM attached to successfully operate.

TPM module is now on sale at: http://storefarm.naver.com/cubebite . For detailed information, please refer to wiki page.

## Q&A

You can ask questions about these codes at https://groups.google.com/forum/#!forum/devicesecurityplatform

## build howto's

### to build for raspberrypi 3, do:

    wget https://raw.githubusercontent.com/SecurityPlatformCoKr/meta-sp/master/meta-sp-raspberrypi/utils/setup/setup.sh
    sh setup.sh

to complete image build, you should go to poky/meta-iot-black/keys and do:

    cd poky/meta-iot-black/keys
    sh README
    cd -

Finally, you can run build command:

    source poky/oe-init-build-env
    bitbake sp-rpi-image

### to build for raspberry pi 2, do:
after setting up for raspberrypi 3, change only 1 character:
in build/conf/localconf, chage
    MACHINE="raspberrypi3"
to
    MACHINE="raspberrypi2"
