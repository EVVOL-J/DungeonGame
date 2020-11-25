package ru.geekbrains.dungeon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.dungeon.game.GameController;
import ru.geekbrains.dungeon.game.GameMap;
import ru.geekbrains.dungeon.helpers.Assets;
import ru.geekbrains.dungeon.screens.ScreenManager;

public class DungeonGame extends Game {
    private SpriteBatch batch;

    // Домашнее задание:
    // +1. Разобраться с кодом ( в пакете ru.geekbrains.dungeon.game )
    // +2. Необходимо вывести на экран: имя персонажа, количество монет
    // Я понял это задание так, что только у героя нужно вывести имя и монеты, а у монстров имя,
    // т к у мостров кроме имени нет смысла показывать монеты.
    // Причем, наверное, будет сделать лучше отображение статута героя в углу экрана.
    //  В Unit создал поле монеты.
    // Поле имя перенёс в класс Unit и у каждого персонажа над головой его имя. Отображение в углу экрана оставил в WorldRenderer.
    // +3. Если жизнь персонажа 100% то полоска жизни должна отрисовываться с альфа 0.2
    // Проверка в Unit при задании barY, сделал более емкую конструкцию чтобы не нагромождать if
    // +4. При убийстве монстра персонаж может получить 1-3 монеты
    // При убийстве все монеты передаются убившему. Изначально у героя 0, у монстров 1-3;
    // Реализация в Unit при атаке.
    // +5. Попробуйте посчитать раунды ( каждый раз, когда ход переходит к игроку
    // номер раунда должен увеличиваться )
    // Поле раунд в UnitController, там уже была проверка когда ход передается 0 персонажу,
    // просто добавлен инкремент, так же добавлено отображение в углу экрана.
    // +-6. В начале 3 раунда должен появиться новый монстр ( * каждого третьего )
    // Для того чтобы монстр не появился на персонаже или в запрещенной зоне, необходимо переделать activate MonsterController
    // я сделал так, в activate делаю проверку и возвращаю boolean активировал ли. isCellEmpty перенёс в UnitController.
    //добавил в UnitController метод активации нужного числа монстров в рандомных точках;
    // Также необходимо что-то у UnitController с листом пользователя, чтобы удобно апдейтить,
    // при добавленнии нового монстра пока костыль.
    // +7. В начале хода персонажи восстанавливают 1 хп.
    // Там же где и раунды, вызывается метод hillAllUnit который вызывает в Unit hillUnit, куда передаем сколько здоровья пополнить.

    @Override
    public void create() {
        batch = new SpriteBatch();
        ScreenManager.getInstance().init(this, batch);
        ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAME);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float dt = Gdx.graphics.getDeltaTime();
        getScreen().render(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
