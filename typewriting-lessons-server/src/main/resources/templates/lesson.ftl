<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu+Mono&display=swap" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/css/styles.css">

	<script src="/js/lesson.js"></script>
</head>
<body>
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-auto">
                <div><u>Cvičení ${lesson_number}</u></div>
                <div>-----------------------------------------------------------------------------------------------------------------------------</div>
                <div>${lesson_instructions}</div>
                <div>-----------------------------------------------------------------------------------------------------------------------------</div>
                <div>${lesson_pattern}</div>
                <div>-----------------------------------------------------------------------------------------------------------------------------</div>
				<form>
					<div class="form-group">
						<input id="pattern_input" type="hidden" name="pattern" value="${lesson_pattern}"/>
						<textarea id="text_input" class="form-control" rows="2"></textarea>
					</div>
				</form>
            </div>
        </div>
    </div>
</body>
</html>