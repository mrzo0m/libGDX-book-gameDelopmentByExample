package com.github.br.gdx.simple.visual.novel;

import com.github.br.gdx.simple.visual.novel.api.ElementId;
import com.github.br.gdx.simple.visual.novel.api.Pair;
import com.github.br.gdx.simple.visual.novel.api.context.PlotContext;
import com.github.br.gdx.simple.visual.novel.api.edge.Predicate;
import com.github.br.gdx.simple.visual.novel.api.plot.DefaultSceneManager;
import com.github.br.gdx.simple.visual.novel.api.plot.Plot;
import com.github.br.gdx.simple.visual.novel.api.plot.PlotConfig;
import com.github.br.gdx.simple.visual.novel.api.plot.SceneSupplier;
import com.github.br.gdx.simple.visual.novel.api.scene.*;
import com.github.br.gdx.simple.visual.novel.impl.TestNode;
import com.github.br.gdx.simple.visual.novel.impl.TestScreenManager;
import com.github.br.gdx.simple.visual.novel.impl.TestUserContext;
import org.junit.Assert;
import org.junit.Test;

public class PlotOneSceneTest {


    @Test
    public void testPlotWithOneSceneOneNode() {
        TestUserContext userContext = new TestUserContext();
        TestScreenManager testScreenManager = new TestScreenManager();
        final SceneBuilder<TestUserContext, TestScreenManager> sceneBuilder = Scene.builder(
                SceneConfig.<TestScreenManager>builder()
                        .setScreenManager(testScreenManager)
                        .build()
        );

        ElementId node1 = sceneBuilder.registerNode(new TestNode<TestUserContext, TestScreenManager>());
        final Scene<TestUserContext, TestScreenManager> scene = sceneBuilder.graph()
                .node(node1).end()
                .build();

        System.out.println(scene.toString());

        ElementId one = ElementId.of("one");
        Plot<TestUserContext, TestScreenManager> plot = Plot.<TestUserContext, TestScreenManager>builder(PlotConfig.builder().build())
                .setSceneManager(new DefaultSceneManager<TestUserContext, TestScreenManager>()
                        .addScene(one, new SceneSupplier<TestUserContext, TestScreenManager>() {

                                    @Override
                                    public Scene<TestUserContext, TestScreenManager> get() {
                                        return scene;
                                    }
                                }
                        ))
                .setBeginSceneId(one)
                .build();


        boolean result = plot.execute(1f, userContext);
        Assert.assertTrue(result);
    }

    @Test
    public void testPlotWithOneSceneOneNodeTwoExecute() {
        TestUserContext userContext = new TestUserContext();
        TestScreenManager testScreenManager = new TestScreenManager();
        final SceneBuilder<TestUserContext, TestScreenManager> sceneBuilder = Scene.builder(
                SceneConfig.<TestScreenManager>builder()
                        .setScreenManager(testScreenManager)
                        .build()
        );

        ElementId node1 = sceneBuilder.registerNode(new TestNode<TestUserContext, TestScreenManager>());

        final Scene<TestUserContext, TestScreenManager> scene = sceneBuilder.graph()
                .node(node1).end()
                .build();

        System.out.println(scene.toString());

        ElementId one = ElementId.of("one");
        Plot<TestUserContext, TestScreenManager> plot = Plot.<TestUserContext, TestScreenManager>builder(PlotConfig.builder().build())
                .setSceneManager(new DefaultSceneManager<TestUserContext, TestScreenManager>()
                        .addScene(one, new SceneSupplier<TestUserContext, TestScreenManager>() {

                                    @Override
                                    public Scene<TestUserContext, TestScreenManager> get() {
                                        return scene;
                                    }
                                }
                        ))
                .setBeginSceneId(one)
                .build();

        plot.execute(1f, userContext);
        boolean result = plot.execute(1f, userContext);
        Assert.assertTrue(result);
    }

    @Test
    public void testPlotWithOneScene_N_Nodes() {
        TestUserContext userContext = new TestUserContext();
        TestScreenManager testScreenManager = new TestScreenManager();
        final SceneBuilder<TestUserContext, TestScreenManager> sceneBuilder = Scene.builder(
                SceneConfig.<TestScreenManager>builder()
                        .setScreenManager(testScreenManager)
                        .build()
        );

        ElementId node1 = sceneBuilder.registerNode(new TestNode<TestUserContext, TestScreenManager>());
        ElementId node2 = sceneBuilder.registerNode(new TestNode<TestUserContext, TestScreenManager>());
        ElementId node3 = sceneBuilder.registerNode(new TestNode<TestUserContext, TestScreenManager>());
        ElementId node4 = sceneBuilder.registerNode(new TestNode<TestUserContext, TestScreenManager>());

        final Scene<TestUserContext, TestScreenManager> scene = sceneBuilder.graph()
                .node(node1).to()
                .node(node2).to()
                .node(node3).to()
                .node(node4).end()
                .build();

        System.out.println(scene.toString());

        ElementId one = ElementId.of("one");
        Plot<TestUserContext, TestScreenManager> plot = Plot.<TestUserContext, TestScreenManager>builder(PlotConfig.builder().build())
                .setSceneManager(new DefaultSceneManager<TestUserContext, TestScreenManager>()
                        .addScene(one, new SceneSupplier<TestUserContext, TestScreenManager>() {

                                    @Override
                                    public Scene<TestUserContext, TestScreenManager> get() {
                                        return scene;
                                    }
                                }
                        ))
                .setBeginSceneId(one)
                .build();

        plot.execute(1f, userContext);
        plot.execute(1f, userContext);
        plot.execute(1f, userContext);
        boolean result = plot.execute(1f, userContext);
        Assert.assertTrue(result);
    }

    @Test
    public void testPlotWithOneScene_N_Nodes_FSM_is_not_Finished() {
        TestUserContext userContext = new TestUserContext();
        TestScreenManager testScreenManager = new TestScreenManager();
        final SceneBuilder<TestUserContext, TestScreenManager> sceneBuilder = Scene.builder(
                SceneConfig.<TestScreenManager>builder()
                        .setScreenManager(testScreenManager)
                        .build()
        );

        ElementId node1 = sceneBuilder.registerNode(new TestNode<TestUserContext, TestScreenManager>());
        ElementId node2 = sceneBuilder.registerNode(new TestNode<TestUserContext, TestScreenManager>());
        ElementId node3 = sceneBuilder.registerNode(new TestNode<TestUserContext, TestScreenManager>());
        ElementId node4 = sceneBuilder.registerNode(new TestNode<TestUserContext, TestScreenManager>());

        final Scene<TestUserContext, TestScreenManager> scene = sceneBuilder.graph()
                .node(node1).to()
                .node(node2).to()
                .node(node3).to()
                .node(node4).end()
                .build();

        System.out.println(scene.toString());

        ElementId one = ElementId.of("one");
        Plot<TestUserContext, TestScreenManager> plot = Plot.<TestUserContext, TestScreenManager>builder(PlotConfig.builder().build())
                .setSceneManager(new DefaultSceneManager<TestUserContext, TestScreenManager>()
                        .addScene(one, new SceneSupplier<TestUserContext, TestScreenManager>() {

                                    @Override
                                    public Scene<TestUserContext, TestScreenManager> get() {
                                        return scene;
                                    }
                                }
                        ))
                .setBeginSceneId(one)
                .build();

        plot.execute(1f, userContext);
        plot.execute(1f, userContext);
        boolean result = plot.execute(1f, userContext);
        Assert.assertFalse(result);
    }

    /**
     * digraph graphname {
     * a;
     * b;
     * c;
     * d;
     * a -> b;
     * b -> c;
     * b -> d;
     * c -> a;
     * }
     */
    @Test
    public void testPlotWithCycleJumpToPast() {
        final TestUserContext userContext = new TestUserContext();
        TestScreenManager testScreenManager = new TestScreenManager();
        final SceneBuilder<TestUserContext, TestScreenManager> sceneBuilder = Scene.builder(
                SceneConfig.<TestScreenManager>builder()
                        .setScreenManager(testScreenManager)
                        .build()
        );

        ElementId a = sceneBuilder.registerNode(ElementId.of("a"), new TestNode<TestUserContext, TestScreenManager>());
        ElementId b = sceneBuilder.registerNode(ElementId.of("b"), new TestNode<TestUserContext, TestScreenManager>());
        final ElementId c = sceneBuilder.registerNode(ElementId.of("c"), new TestNode<TestUserContext, TestScreenManager>());
        final ElementId d = sceneBuilder.registerNode(ElementId.of("d"), new TestNode<TestUserContext, TestScreenManager>());

        final Scene<TestUserContext, TestScreenManager> scene = sceneBuilder.graph()
                .node(a).to()
                .node(b)
                .to(
                        new Pair<ElementId, Predicate<TestUserContext, TestScreenManager>>(c, new Predicate<TestUserContext, TestScreenManager>() {
                            @Override
                            public boolean test(PlotContext<TestUserContext, TestScreenManager> context) {
                                return c == context.getUserContext().nextId;
                            }
                        }),
                        new Pair<ElementId, Predicate<TestUserContext, TestScreenManager>>(d, new Predicate<TestUserContext, TestScreenManager>() {
                            @Override
                            public boolean test(PlotContext<TestUserContext, TestScreenManager> context) {
                                return d == context.getUserContext().nextId;
                            }
                        })
                )
                .node(c).to(a).endBranch()
                .node(d).end()
                .build();
        System.out.println(scene.toString());

        ElementId one = ElementId.of("one");
        Plot<TestUserContext, TestScreenManager> plot = Plot.<TestUserContext, TestScreenManager>builder(PlotConfig.builder().build())
                .setSceneManager(new DefaultSceneManager<TestUserContext, TestScreenManager>()
                        .addScene(one, new SceneSupplier<TestUserContext, TestScreenManager>() {
                                    @Override
                                    public Scene<TestUserContext, TestScreenManager> get() {
                                        return scene;
                                    }
                                }
                        ))
                .setBeginSceneId(one)
                .build();

        plot.execute(1f, userContext); // a
        userContext.nextId = c;
        plot.execute(1f, userContext); // b
        plot.execute(1f, userContext); // c
        plot.execute(1f, userContext); // a
        userContext.nextId = d;
        plot.execute(1f, userContext); // b
        boolean result = plot.execute(1f, userContext); // d
        Assert.assertTrue(result);
    }

    /**
     * digraph graphname {
     * a;
     * b;
     * c;
     * d;
     * a -> b;
     * b -> c;
     * b -> d;
     * c -> a;
     * }
     */
    @Test
    public void testPlotWithCycleJumpToFuture() {
        final TestUserContext userContext = new TestUserContext();
        TestScreenManager testScreenManager = new TestScreenManager();

        final SceneBuilder<TestUserContext, TestScreenManager> sceneBuilder = Scene.builder(
                SceneConfig.<TestScreenManager>builder()
                        .setScreenManager(testScreenManager)
                        .build()
        );

        ElementId a = sceneBuilder.registerNode(ElementId.of("a"), new TestNode<TestUserContext, TestScreenManager>());
        ElementId b = sceneBuilder.registerNode(ElementId.of("b"), new TestNode<TestUserContext, TestScreenManager>());
        ElementId c = sceneBuilder.registerNode(ElementId.of("c"), new TestNode<TestUserContext, TestScreenManager>());
        final ElementId d = sceneBuilder.registerNode(ElementId.of("d"), new TestNode<TestUserContext, TestScreenManager>());

        SceneNodeBuilder<TestUserContext, TestScreenManager> graphBuilder = sceneBuilder.graph();
//                .node(a)
//                    .to(b, d)
//                .node(b)
//                    .to(c, d)
//                .node(c).to(a).endBranch()
//                .node(d).end()
//                .build();

        // основной сценарий
        graphBuilder.node(a).to().node(b).to().node(d).end();

        // второстепенные сюжетные завороты
        graphBuilder.node(a).to(d, new Predicate<TestUserContext, TestScreenManager>() {
            @Override
            public boolean test(PlotContext<TestUserContext, TestScreenManager> context) {
                return (userContext.nextId == d);
            }
        }).endBranch(); // заворот вперед
        graphBuilder.node(b).to().node(c).to(a).endBranch(); // заворот назад

        final Scene<TestUserContext, TestScreenManager> scene = graphBuilder.build();
        System.out.println(scene.toString());

        ElementId one = ElementId.of("one");
        Plot<TestUserContext, TestScreenManager> plot = Plot.<TestUserContext, TestScreenManager>builder(PlotConfig.builder().build())
                .setSceneManager(new DefaultSceneManager<TestUserContext, TestScreenManager>()
                        .addScene(one, new SceneSupplier<TestUserContext, TestScreenManager>() {
                                    @Override
                                    public Scene<TestUserContext, TestScreenManager> get() {
                                        return scene;
                                    }
                                }
                        ))
                .setBeginSceneId(one)
                .build();

        userContext.nextId = d;
        plot.execute(1f, userContext); // a
        boolean result = plot.execute(1f, userContext); // d
        Assert.assertTrue(result);
    }

}
