package consumer

import com.example.myapplication.consumer.decorators.PremiumAvailableDecorator
import com.example.myapplication.consumer.decorators.factories.PremiumDecoratorFactory
import com.example.myapplication.consumer.events.Form
import com.example.myapplication.consumer.events.SaveFormEvent
import com.example.myapplication.consumer.events.SaveType
import com.example.myapplication.consumer.publishers.SaveFormPluginsMultiPublisherConsumer
import com.example.myapplication.consumer.publishers.SaveFormPluginsSinglePublisherConsumer
import com.example.myapplication.plugin.configs.PluginInfo
import com.example.myapplication.plugin.decorators.PluginDecorator
import com.example.myapplication.plugin.plugins.Plugin
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class ConsumerTest {

    @get:Rule
    val mockRule = MockKRule(this)

    @Test
    fun `on single publish event success`() = runTest {
        val event = SaveFormEvent(Form("1", "Title"))
        val plugin = spyk<Plugin<SaveFormEvent>>() {
            every { pluginInfo }.returns(TestData.defaultPluginInfo)
            every { name }.returns(SaveType.SAVE_FORM_LOCAL.title)
            coJustRun { process(event) }
        }
        val plugins = buildSet {
            add(plugin)
            generateSequence(0) { it + 1 }.take(2).forEach {
                add(spyk<Plugin<SaveFormEvent>>() {
                    every { pluginInfo }.returns(TestData.defaultPluginInfo)
                    every { name }.returns("Plugin-$it")
                    coJustRun { process(event) }
                })
            }
        }
        val publisher = SaveFormPluginsSinglePublisherConsumer(plugins)

        publisher.onPublishEvent(event)

        coVerify { plugin.process(event) }
    }

    @Test
    fun `on multi publish event success`() = runTest {
        val event = SaveFormEvent(Form("1", "Title"))

        val plugins = buildSet {
            generateSequence(0) { it + 1 }.take(3).forEach {
                add(spyk<Plugin<SaveFormEvent>>() {
                    every { pluginInfo }.returns(TestData.defaultPluginInfo)
                    every { name }.returns("Plugin-$it")
                    every { order }.returns(0)
                    coJustRun { process(event) }
                })
            }
        }

        val publisher = SaveFormPluginsMultiPublisherConsumer(plugins)

        publisher.onPublishEvent(event)

        plugins.forEach {
            coVerify { it.process(event) }
        }
    }

    @Test
    fun `if plugin not include when publish event then plugin not work`() = runTest {
        val event = SaveFormEvent(Form("1", "Title"))

        val plugins = buildSet {
            generateSequence(0) { it + 1 }.take(3).forEach {
                add(spyk<Plugin<SaveFormEvent>>() {
                    every { pluginInfo }.returns(object : PluginInfo {
                        override var isInclude: Boolean = Random.nextBoolean()
                    })
                    every { name }.returns("Plugin-$it")
                    every { order }.returns(0)
                    coJustRun { process(event) }
                })
            }
        }

        val publisher = SaveFormPluginsMultiPublisherConsumer(plugins)

        publisher.onPublishEvent(event)

        plugins.filter { it.pluginInfo.isInclude }.forEach {
            coVerify { it.process(event) }
        }
        plugins.filterNot { it.pluginInfo.isInclude }.forEach {
            coVerify(inverse = true) { it.process(event) }
        }
    }


}

private object TestData {
    val defaultPluginInfo = object : PluginInfo {
        override var isInclude: Boolean = true
    }
}