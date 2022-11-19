<?php
$ru_lang = ["Наименование", "Автор", "Скачать", "Добавить файл", "Светлая", "Темная", "Русский", "Английский", "Файл", "Загрузить", "*Клик*", "Введите свое имя:", "Язык", "Тема", "Обменник", "Сменить имя"];
$en_lang = ["Name", "Author", "Download", "Add File", "Light", "Dark", "Russian", "English", "File", "Upload", "*Click*", "Enter your name:", "Language", "Theme", "Exchanger", "Change name"];
$dark_theme = ['table-dark', 'grey', 'bg-dark'];
$light_theme = ['table-light', 'white', 'bg-light'];


if ($_COOKIE['lan'] == 'en')
    $list = $en_lang;
else
    $list = $ru_lang;

if ($_COOKIE['theme'] == 'dark')
    $theme = $dark_theme;
else
    $theme = $light_theme;



function addNavBar($list, $theme){
    echo '<nav class="navbar navbar-expand-lg '.$theme[2].'">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.php">'.$list[14].'</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
            <a class="nav-link" href="#" onclick="var inputname = prompt(\''.$list[11].'\');document.cookie=\'name=\'+ inputname;" >'.$list[15].'</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            '.$list[12].'
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="index.php" onclick="document.cookie=\'lan=ru\';">'.$list[6].'</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="index.php" onclick="document.cookie=\'lan=en\';">'.$list[7].'</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            '.$list[13].'
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="index.php" onclick="document.cookie=\'theme=light\';">'.$list[4].'</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="index.php" onclick="document.cookie=\'theme=dark\';">'.$list[5].'</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>';

}
echo '<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>';

echo "<body style='background-color:$theme[1]'>";
addNavBar($list, $theme);
if (!isset($_COOKIE['name']))
    getName($list[11]);

if (!isset($_COOKIE['theme']) or ( $_COOKIE['theme'] != 'light' and $_COOKIE['theme'] != 'dark' ) )
    setcookie("theme", "light");

if (!isset($_COOKIE['lan']) or ( $_COOKIE['lan'] != 'ru' and $_COOKIE['lan'] != 'en' ) )
    setcookie("lan", "ru");

function  getName($string){
    echo '<script type="text/javascript"> ';
    echo 'var inputname = prompt("'.$string.'", "");';
    echo 'document.cookie = "name=" + inputname;';
    echo '</script>';
}

session_start();
$con = new mysqli("MYSQL", "user", "toor", "appDB");


echo '<table class="table table-hover '.$theme[0].'">
        <tr><th>'.$list[0].'</th><th>'.$list[1].'</th><th>'.$list[2].'</th></tr>';
$result = $con->query("SELECT * FROM files");
foreach ($result as $row){
    echo "<tr><td>{$row['title']}.{$row['type']}</td><td>{$row['author']}</td><td><a href='download.php?id={$row['ID']}'>{$list[10]}</a></td></tr>";
}
echo '</table><br>';
echo '<form enctype="multipart/form-data" action="upload.php" method="POST">
<div class="form-group">
<input type="text" required name="title" id="title" placeholder="'.$list[0].'"/>
    '.$list[8].': <input name="uploadedFile" type="file" />
    <input required type="submit" value="'.$list[9].'" />
</form>
</div>';
?>