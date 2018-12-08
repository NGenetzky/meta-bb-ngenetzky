FROM crops/yocto:ubuntu-18.04-base
COPY ./ /home/yoctouser/
RUN cd /home/yoctouser \
    && /home/yoctouser/ci/gitlab_build_pre_install.sh
RUN cd /home/yoctouser \
    && /home/yoctouser/ci/gitlab_build.sh
RUN cd /home/yoctouser \
    && /home/yoctouser/ci/gitlab_test.sh
