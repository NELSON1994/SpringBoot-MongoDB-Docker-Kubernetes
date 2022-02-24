<!DOCTYPE html>
<html>
<head><title>student app</title>
    <link rel="stylesheet" href="nelson.min.css">
</head>
<body class="ph3 pt0 pb4 mw7 center sans-serif">
<h1 class="f2 mb0"><span class="gold">k</span>student</h1>
<p class="f5 mt1 mb4 lh-copy">A simple Student Details app.</p>
<form action="/student" method="POST" enctype="multipart/form-data">
    <ol class="list pl0">
        <li class="mv3">
            <label class="f6 b db mb2" for="image">Upload an image</label>
            <input class="f6 link dim br1 ba b--black-20 ph3 pv2 mb2 dib black bg-white pointer" type="file"
                   name="image">
            <input class="f6 link dim br1 ba bw1 ph3 pv2 mb2 dib black bg-white pointer ml2" type="submit"
                   value="Upload" name="upload">
        </li>
        <li class="mv3"><label class="f6 b db mb2" for="description">Write some few characters that describes you here</label>
            <textarea class="f4 db border-box hover-black w-100 measure ba b--black-20 pa2 br2 mb2" rows="5"
                      name="description"><#if description??>${description}</#if></textarea>
            <input class="f6 link dim br1 ba bw1 ph3 pv2 mb2 dib black bg-white pointer" type="submit" value="Publish"
                   name="publish">
        </li>
    </ol>
</form>
<ul class="list pl0"><p class="f6 b db mb2">Students</p>
    <#if students??>
        <#list students as student>
            <li class="mv3 bb bw2 b--light-yellow bg-washed-yellow ph4 pv2"><p class="measure"></p>
                <p>${student}</p>
                <p></p>
            </li>
        <#else>
            <p class="lh-copy f6">You don't have any student added.</p>
        </#list>
    </#if>
</ul>
</body>
</html>