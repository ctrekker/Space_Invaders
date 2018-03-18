<?php
    $score=(int)$_GET["score"];
    $name=$_GET["name"];
    if(strlen($name)<=16) {
        $data=file_get_contents("data.txt");
        $json_data=json_decode($data, true);
        $json_data["score"]=$score;

        $highscores=$json_data["highscores"];
        $score_lowest=(int)$highscores[0]["score"];
        $index=0;
        $score_lowest_index=0;
        foreach($highscores as $record) {
            if((int)$record["score"]<$score_lowest) {
                $score_lowest=(int)$record["score"];
                $score_lowest_index=$index;
            }
            $index++;
        }
        if($score>$score_lowest) {
            $highscores[$score_lowest_index]["score"]=$score;
            $highscores[$score_lowest_index]["name"]=$name;

            $json_data["scoreAdded"]=true;
        }
        else {
            $json_data["scoreAdded"]=false;
        }
        $json_data["highscores"]=$highscores;
        file_put_contents("data.txt", json_encode($json_data));
    }
    echo file_get_contents("data.txt");
?>