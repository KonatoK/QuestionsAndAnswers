#!/bin/sh
pluginname="QuestionsAndAnswers"
#get version according to git
#Plugin version in format of v1.2-dev-8f63cd1 (tag-dirty-commit)
#The dev only appears if the working dir is dirty
pluginversion=`git describe --abbrev=0`
pluginversion=$pluginversion"-"
pluginversion=$pluginversion`[[ $(git diff --shortstat 2> /dev/null | tail -n1) != "" ]] && echo "dev-"`
pluginversion=$pluginversion`git --no-pager log -1 --pretty=format:'%h'`
#do stuff
echo "Packaging $pluginname $pluginversion in a jar file..."
jar cfM $pluginname\ $pluginversion.jar -C resources . -C build .
